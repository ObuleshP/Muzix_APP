FROM openjdk:8

ENV MONGO_URI=mongodb://localhost:27017/musix

ADD ./target/Musix_Favourite_Recommend-0.0.1-SNAPSHOT.jar Musix_Favourite_Recommend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "Musix_Favourite_Recommend-0.0.1-SNAPSHOT.jar" ]
