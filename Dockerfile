FROM openjdk
WORKDIR /hilos
COPY . /hilos
RUN javac Main.java 
ENTRYPOINT ["java", "Main"]
CMD ["1","1"]