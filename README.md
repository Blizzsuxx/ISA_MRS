# ISA_MRS



# DevOps services



SonarCloud: https://sonarcloud.io/dashboard?id=ec2b33cd8152b93fa7abadc79c1fc022f81c54cd
Heroku : https://ap-tim-13-fe.herokuapp.com/ap/prijava

# Developer Teams

Team| Student
--- | ---
Nataša Rajtarov | Student 1
Andrija Vojnović | Student 2 
Dragan Arsić | Student 3
Marko Bjelica | Student 4

## Installation
Program se za lokal skida sa develop grane. Na main grani se nalazi projekat za produkciju.
Prvo je potrebno se ima instaliran Postrgres. Zatim se ulazi u PgAdmin i kreira se baza. Nakon kreiranja baze konfigurisati application.properties tako da u spring.datasource.url=jdbc:postgresql://localhost:5432/<naziv baze>
  Potrebno je podesiti username i password: spring.datasource.username=username
spring.datasource.password=password 
  Nakon što je to podešeno potrebno je u terminalu pokrenuti komandu npm run install, zatim npm run serve.
  Potrebno je pokrenuti back deo iz InteliJ ili Eclipse okruženja.

  
  
  #DevOps
  Na heroku su kreirane dvije aplikacije , jedna za frontend i druga za backend. .travis.yml sadrzi skriptu za frontend kada se vrsi deploy frontenda i skriptu za backend kada se vrsi deploy backenda. Skripta ne sadrzi u isto vrijeme skripte za pokretanje obe aplikacije jer Heroku brani konkurentni deploy.
