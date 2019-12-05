Memento Docker Findroof

1)	Création et démarrage des container et images

Step 1 :

Créer un reseau docker :
docker network create findroof-mysql
On peut lister tous les réseaux présents pour vérifier :
 

Step 2 : 

Créer un containeur pour notre BBD
docker container run --name mysqldb --network findroof-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=dbfindroof -d mysql:8
•	name of the mysql container
•	We want to create the Database named dbfinroof.
•	specify the network findroof-mysql on which this container should be created.
•	start the container in detached mode.
On peut verifier les logs liée au container pour verifier le bon démarrage :
 
Enfin on peut vérifier que le container exécute bien notre instance de Mysql :
docker container exec -it mysql bash
 

Step 3 On s’attaque à la partie application :

Modifier le nom de la base de donnée dans application.poperties:
 
On a créé un nouveau container, il faut donc indiquer que la base de donnée est joignable sur ce nouveau container.

Step 4:

Creation du Dockerfile à la racine de notre application.
 
C’est sur ce fichier que docker va s’appuyer pour executer notre application contairisée.

Step 5:

Build l’application avec Maven, en respectant cette config :
  
  - Goal: install
  - ingnorer les tests

Step 6 :

Création d’une image de notre application :
docker image build -t findroof .
 

Step 7:

Il ne reste plus qu’à lancer l’application de notre container :
docker container run --network findroof-mysql --name findroof-container -p 8080:8080 -d findroof

Step 8:

Vérification que l’application est lancée conrrectement : 
 
Ou ae sont les 2 premiers caractères de notre container id.
 
Puis sur un navigateur :
 
 
A retenir :

Nous avons :
•	Créer un réseau pour nos containers afin qu’ils puissent communiquer ensemble
•	Run une image de Mysql à partir du dockerhub dans un container
•	Configuré un Dockerfile permettant de build notre application avec les bons parametres
•	Run une image notre application après l’avoir build avec maven dans un dexieme container
•	Attaché nos container au docker réseau

2) Import export d’images depuis le file system :

Export des images:

docker image ls
 

docker save findroof > findroof.tar

Import d’une image:

docker load < findroof.tar



