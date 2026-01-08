## Docker를 이용하여 RabbitMQ 컨테이너 띄우는 방법
[RabbitMQ 공식문서](https://www.rabbitmq.com/docs/download)를 확인해보면 Docker를 이용해서 RabbitMq, RabbitMq Management(관리자 페이지)를 사용할 수 있도록 컨테이너를 띄울 수 있습니다.<br/>
명령어는 아래와 같습니다.
```shell
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management
```
rabbitMQ 기본 username, password, port는 아래와 같습니다.
- username : guest
- password : guest
- port : 5672


