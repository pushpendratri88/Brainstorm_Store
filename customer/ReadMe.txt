Radis Cache Implementation Ref:

Radis Implementation - https://www.geeksforgeeks.org/spring-boot-caching-with-redis/
Refer Radis installation on Docker  - https://www.geeksforgeeks.org/installation-and-starting-the-servers-of-redis-stack-using-docker/

Work with Docker in Ubuntu(grant permission and run Docker image in Ubuntu):
1. Add an Ubuntu user to the docker group -
    - sudo usermod -aG docker akshu
2. The following command helps in knowing whether current add user assigned to docker group or not:
    - getent group docker
3. Refresh the group permission to use updated one with running following command:
    - newgrp docker
4. Restart the docker daemon which is already running. After restarting only the changes will comes into effect.
    - sudo service docker restart
5. Leave the current SSH terminal and re-login with SSH. then carry out.
    - docker ps
    -   akshu@akshu-MS-7D99:~$ docker ps
        CONTAINER ID   IMAGE                      COMMAND            CREATED         STATUS         PORTS                                                                                  NAMES
        ac7dabce6c36   redis/redis-stack:latest   "/entrypoint.sh"   5 minutes ago   Up 5 minutes   0.0.0.0:6379->6379/tcp, :::6379->6379/tcp, 0.0.0.0:8001->8001/tcp, :::8001->8001/tcp   silly_mendel

6.
    (Run docker with new name (one time run))- docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
    (Docker with new name is already created  then run )- docker run -d -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
        - akshu@akshu-MS-7D99:~$ docker ps
          CONTAINER ID   IMAGE                      COMMAND            CREATED         STATUS         PORTS                                                                                  NAMES
          ac7dabce6c36   redis/redis-stack:latest   "/entrypoint.sh"   7 minutes ago   Up 7 minutes   0.0.0.0:6379->6379/tcp, :::6379->6379/tcp, 0.0.0.0:8001->8001/tcp, :::8001->8001/tcp   silly_mendel

7. Run this command to connect to the server using redis-cli
    - docker exec -it silly_mendel redis-cli
        - akshu@akshu-MS-7D99:~$ docker exec -it silly_mendel redis-cli
          127.0.0.1:6379>
#######################################################################################################

Loging in MS - https://www.youtube.com/watch?v=bphMYrTv8pA

