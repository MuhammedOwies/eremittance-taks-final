# E-Vision Technical Task

### Reference Documentation
This project is mainly for part of Remittance operations and data manipulation service under security access.

### Tools and Libraries used:
* Lombok
* JPA
* H2 - as DB source
* Spring Security
* JWT - user for authorization
* GZIP - used for compressing data transferred over HTTP
* Model-Mapper - used to map entity and view objects to each other
* JavaX Validation - used to validate requests vs requirements
* Mockito + JUnit4 - used for unit testing

### Questions and Answers
* List of the tools/libraries you will be using in such implementation. 
Mentioned above.
* How are you handling design concepts such as loosely-coupled and high cohesive classes and objects.
  Achevied by making use of Spring boot framework.
* How are you planning to deal with huge data transmitted over HTTP(s).
  Achevied by using GZIP to compress Data (GZip compression is a very simple and effective way to save bandwidth and reduce the response time.)
* What configurations you will place regarding expected challenges.
  
* How you are you validating and verifying the request.
  Achevied by using javax validation and spring security for verification.
* What security checks\validations you imagine to be placed.
  Fields validation (ex email,phone.etc) and user Authentication.
  
### Answers of the following questions within implementation
* If it is required to route the object to another service/module, how would you do that?
  Could be Achevied by using microservices concepts.
* What approach\tools you will be using?
  rest template to communicate with other modules.
* What are your concerns about performance and memory usage?
  Applying flyweight design pattern will handle memory and performance.
* What framework(s) you recommend to use?
  Spring ecosystem.
* How much estimation you will ask to deliver a fully-fledged component that is ready for deployment to production?
  Regarding to full business process understanding, According to current state it will take maximum a week.
