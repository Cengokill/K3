package Test;

import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.distribution.UniformDistribution;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

// https://youtu.be/cEWyjcceSG4?t=660
// https://github.com/eclipse/deeplearning4j-examples/blob/master/dl4j-examples/src/main/java/org/deeplearning4j/examples/quickstart/modeling/convolution/CIFARClassifier.java
// https://deeplearning4j.konduit.ai/multi-project/how-to-guides/beginners

public class Xor {
	public static void main (String... argc) {
		//configuration
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
		//network
		/**
		 * 1, 0 - true
		 * 0, 1 - true
		 * 0, 0 - true
		 * 1, 1 - true
		 */
		//dataset
		//remplir 1000 epoques
		//evaluation
		
	}

}
