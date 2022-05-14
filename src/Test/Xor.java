package Test;

import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.distribution.UniformDistribution;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.nd4j.evaluation.classification.Evaluation;
// https://youtu.be/cEWyjcceSG4?t=660
// https://github.com/eclipse/deeplearning4j-examples/blob/master/dl4j-examples/src/main/java/org/deeplearning4j/examples/quickstart/modeling/convolution/CIFARClassifier.java
// https://deeplearning4j.konduit.ai/multi-project/how-to-guides/beginners

public class Xor {
	public static void main (String... argc) {
		//I. Configuration
		int nb_couches_entree=2;
		int nb_couches_sortie=4;
		MultiLayerConfiguration configuration = new NeuralNetConfiguration.Builder()
			.miniBatch(false)
			.updater(new Sgd(0.1))//gradient stochastique
			.list()
			.layer(new DenseLayer.Builder()
					.nIn(nb_couches_entree)
					.nOut(nb_couches_sortie)
					.weightInit(new UniformDistribution(0, 1))
					.activation(Activation.SIGMOID)
					.build())
			.layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
					.nIn(nb_couches_sortie)
					.nOut(nb_couches_entree)
					.activation(Activation.SOFTMAX)
					.weightInit(new UniformDistribution(0, 1)).build())
			.build();
		//2. Network
		MultiLayerNetwork network = new MultiLayerNetwork(configuration);
		/**
		 * 1, 0 - true (0, 1)
		 * 0, 1 - true (0, 1)
		 * 0, 0 - false (1, 0)
		 * 1, 1 - false (1, 0)
		 */
		//3. Dataset
		double[][] features = new double[][] {
			{0, 0},
			{1, 0},
			{0, 1},
			{1, 1}
		};
		
		double [][] labels = new double[][] {
			{1, 0},//false
			{0, 1},//true
			{0, 1},//true
			{1, 0}//false
		};
		
		DataSet ds = new DataSet(Nd4j.create(features), Nd4j.create(labels));
		//4. Remplir 1000 epoques
		for(int i=0; i<100; i++) {
			network.fit(ds);
		}		
		//5. Evaluation
		Evaluation evaluation = new Evaluation();
		evaluation.eval(ds.getLabels(), network.output(ds.getFeatures()));
		System.out.println(evaluation.stats());
	}

}




































































