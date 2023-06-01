# K3
Le K3 est un jeu de stratégie codé ici en **Java Swing** pour deux joueurs. Une IA utilisant l'**algorithme MiniMax** est utilisée lorsqu'on choisit
de jouer contre l'ordinateur.

## Présentation du jeu

Des pions sont tirés au hasard et distribués entre les joueurs, et sur la pyramide centrale du jeu pour former la base de la pyramide.
Chaque joueur reçoit 21 pions de différentes couleurs en début de partie, et les dispose en pyramide sur son camp.

Ceci fait, la partie peut commencer.
À tour de rôle, chaque joueur choisit l'un de ses pions et le place sur la montagne, en respectant les règles suivantes :
le pion choisi doit être accessible, c'est-à-dire qu'il ne doit pas être chevauché par un autre pion. Au premier tour, le
joueur ne peut choisir que le pion situé au sommet de sa pyramide, puis au fur et à mesure de l'avancement de la partie,
il pourra choisir des pions situés plus bas. Le joueur doit placer son pion de manière à chevaucher deux autres pions, dont
au moins un de la même couleur ou de couleur neutre. Les pions blancs permettent de passer son tour, mais il y a une
pénalité si l'on place son pion sur deux pions de la même couleur. Lorsque c'est à son tour de jouer et qu'un joueur ne peut
plus jouer, soit parce qu'il n'a plus de pion, soit parce que les couleurs ne permettent pas de le placer, ce joueur est
éliminé. Le dernier joueur encore en jeu remporte la partie.

**Menu principal du jeu**
![image](https://github.com/Cengokill/K3/assets/61111039/0f21e8ae-2069-42f5-8452-d27a03e30f1a)

**Partie en cours**
![image](https://github.com/Cengokill/K3/assets/61111039/f528931d-a368-4af6-a1b4-986cdec59953)
