# Reactive Stock Trader

My implementation of Reactive Stock Trader system (Portfolio, Broker, Wire Transfers Services) in Scala.

Tools - Lagom (Play, Akka, Cassandra and Kafka), Vue.js (for UI), Kubernetes  
Patterns - Event Sourcing, CQRS  
Architecture - Interface, gateway, services  
Integration - Point to Point, streaming, pub/sub  

I learned and followed the tutorial below published on IBM Developer:

**Reactive in practice: A complete guide to event-driven systems development in Java**
- [Reactive in Practice on IBM Developer](https://developer.ibm.com/technologies/reactive-systems/)

* Unit 1: [Introduction: event storming the 'stock trader' domain](https://developer.ibm.com/tutorials/reactive-in-practice-1/)
* Unit 2: [Prototyping the UI and UI integration patterns](https://developer.ibm.com/tutorials/reactive-in-practice-2/)
* Unit 3: [Translating the domain model to service APIs](https://developer.ibm.com/tutorials/reactive-in-practice-3/)
* Unit 4: [Concurrency, parallelism and asynchrony](https://developer.ibm.com/tutorials/reactive-in-practice-4/)
* Unit 5: [Event sourcing](https://developer.ibm.com/tutorials/reactive-in-practice-5/)
* Unit 6: [CQRS - Write side (commands and state)](https://developer.ibm.com/tutorials/reactive-in-practice-6/)
* Unit 7: [CQRS - Read side (queries and views)](https://developer.ibm.com/tutorials/reactive-in-practice-7/)
* Unit 8: [Integration patterns for transactions](https://developer.ibm.com/tutorials/reactive-in-practice-8/)
* Unit 9: [Microservice integration patterns](https://developer.ibm.com/tutorials/reactive-in-practice-9/)
* Unit 10: [Streaming data](https://developer.ibm.com/tutorials/reactive-in-practice-10/)
* Unit 11: [Deploying and monitoring reactive systems in the cloud](https://developer.ibm.com/tutorials/reactive-in-practice-11/)
* Unit 12: [Recap and conclusion](https://developer.ibm.com/tutorials/reactive-in-practice-12/)  


Reference:

**Java Implementation of Reactive Stock Trader by RedElastic**
- [Reactive stock trader in Java] (https://github.com/RedElastic/reactive-stock-trader)  



# Installation

The following will help you get set up in the following contexts:

- Local development
- Deployment to local Kubernetes (using Minikube)
- Interactions (UI, command line)

## Local development

- Install Java 8 SDK
- [Install sbt](https://www.scala-sbt.org/1.x/docs/Setup.html) (`brew install sbt` on Mac)

This project has been generated by the lagom/lagom-scala.g8 template.
For instructions on running and testing the project, see https://www.lagomframework.com/get-started-scala.html.  

Running Lagom in development mode is simple. Start by launching the backend services using `sbt`.

- `sbt runAll`

The Gateway exposes an API to the frontend on port 9100.

## Deploying to Kubernetes

For instructions on how to deploy Reactive Stock Trader to Kubernetes, you can find the deployment instructions and Helm Charts for Kafka and Cassandra here: [https://github.com/RedElastic/reactive-stock-trader/tree/master/deploy](https://github.com/RedElastic/reactive-stock-trader/tree/master/deploy)

## Interacting with the UI

The UI is developed in Vue.js. You'll need to have [Node.js and npm installed](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm) and then follow the instructions below.

Project setup and launching for development: 

```
npm install
npm run serve
```

This will launch the UI on [localhost:8080](localhost:8080) for development. You can then use the UI to interact with the Lagom system.

Testing / debugging:

- Run your tests: `npm run test`
- Lints and fixes files: `npm run lint`

Reactive Stock Trader uses Rollbar for debugging purposes. In order to make use of Rollbar:

* copy `config.env` to `.env.local`
* sign up at Rollbar and create an access token
* change `VUE_APP_ROLLBAR_ACCESS_TOKEN` to your token in `.env.local`

Visit [Environment Variables and Modes](https://cli.vuejs.org/guide/mode-and-env.html) and [https://rollbar.com](Rollbar) for more details.

For additional Vue configuration information, see [Configuration Reference](https://cli.vuejs.org/config/).  


## Interacting with the command line

If you would like to test the backend without installing the UI, you can use the following command line information to help.

The `jq` command line tool for JSON is very handy for pretty printing JSON responses, on Mac this can be installed with `brew install jq`.

Create a new portfolio named "piggy bank savings":
`PID=$(curl -X POST http:/localhost:9000/api/portfolio -F name="piggy bank savings" | jq -r .portfolioId); echo $PID`

Place an order:
`curl -X POST http://localhost:9000/api/portfolio/$PID/order -F symbol=RHT -F shares=10 -F order=buy`

View the portfolio
`curl http://localhost:9000/api/portfolio/$PID | jq .`

Transfer funds into the portfolio
`curl -X POST http://localhost:9000/api/transfer -F amount=20000 -F sourceType=savings -F sourceId=123 -F destinationType=portfolio -F destinationId=$PID`

```
PID=$(curl -X POST http:/localhost:9000/api/portfolio -F name="piggy bank savings" | jq -r .portfolioId); echo $PID

curl -X POST http://localhost:9000/api/transfer -F amount=20000 -F sourceType=savings -F sourceId=123 -F destinationType=portfolio -F destinationId=$PID

curl http://localhost:9000/api/portfolio/$PID | jq .

curl -X POST http://localhost:9000/api/portfolio/$PID/order -F symbol=IBM -F shares=10 -F order=buy

curl http://localhost:9000/api/portfolio/$PID | jq .
```
