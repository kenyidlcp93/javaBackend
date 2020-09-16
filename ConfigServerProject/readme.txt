//first, to generate .jar execute
mvn clean install

//second, to build docker image, go into ConfigServerProject folder and execute
sudo docker build --tag configserver:1.0 .

//finally, to run docker image
sudo docker run -p 8888:8888/tcp configserver:1.0
