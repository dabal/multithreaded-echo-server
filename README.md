# multithreaded-echo-server

Simple server implementation of echo service with threads. Server run on port 6666. It has 5 Threads for echoing messages send from clients. 

##docker
To build image and run container  
```bash
docker image build -t dabal/multithreaded-echo-server:latest .
docker container run -p 6666:6666 dabal/multithreaded-echo-server:latest
```