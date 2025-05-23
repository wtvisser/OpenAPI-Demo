# API-Design First

This demo provides an insight into API-Design First. It consists of a short theoretical introduction, followed by a code example in Java using OpenAPI and Swagger.

## API Development: A Few Definitions

**API-First**: An approach that treats APIs as the _core focus of development_. APIs are _considered critical business assets_ upon wich the organization operates. API contracts are typically written in a description language such as OpenAPI.

An API and its implementation usually consists of three layers:

```mermaid
block-beta
  columns 1
  a["Implementation<br>(API, Business Logic)"]
  b["Object Model<br>(Domain Objects, Aggregates)"]
  c["Data Model<br>(Tables, Entities)"]
```

**Code-First**: The API specification is implemented in code upon which is iterated. 

In a traditional scenario, a developer is assigned to build a new product feature. Typically, that developer creates the new feature by implementing the business logic first and then connecting that logic to an in-code API design. Thus, the API becomes available for the stakeholders to review only when the developer finishes all the code changes for that feature. Therefore, creating slowness and miscommunication about the API contract review and agreement.

A code-first development process typically looks as follows:

```mermaid
flowchart LR
    A[product feature] --> B[pull request with API design and business logic implementation]
    B --> implement
    implement --> D[publish API]
    D --> E[use API and specification]
    subgraph implement
        direction TB
        C[review pull request] <--> F[code changes]
    end
```

This approach means that this business and object model is created first, whose business logic is then implemented, after which the data model, storage and APIs are created:

```mermaid
block-beta
  columns 4
  space
      a["Object Model<br>(Domain Objects, Aggregates)"]:2
  space
  space:4
  space
      b["Implementation<br>(Business Logic)"]:2
  space
  space:4
  c["Data Model<br>(Tables, Entities)"]:2
  d["Implementation<br>(API)"]:2

a-->b
b-->c
b-->d
```

**Design-First**: The development of an API starts with its design before any code is written. 

In API-Design First development, a designer creates the API contract document before the business logic development phase. That document provides a common language among the product stakeholders to evaluate the effort to build, provide timely feedback, create test use cases, document the API, etc. Thus, we can be more agile by either changing the initial design or moving on with it before wasting any time developing the application.

A design-first development process typically looks as follows:

```mermaid
flowchart LR
    A[product feature] --> B[design API contract]
    B --> design
    design --> D[publish API skeleton code]
    D --> E[use API and specification]
    E --> F[implement business logic]
    F --> implement
    implement --> H[publish business logic]
    subgraph design
        direction TB
        C[review API contract] <--> I[change API contract]
    end
    subgraph implement
        direction TB
        G[review business logic]<--> J[change business logic]
    end
```

With a design-first approach the contract specification is created first, typically as part of the overall system design. Afterwards the object model, business logic, client logic, and data storage can be implemented in parallel:

```mermaid
block-beta
  columns 3
  space
  a["API Contract<br>(Specification)"]
  space
  space:3
  b["Implementation<br>(Business Logic)"]
  c["Object Model<br>(Domain Objects, Aggregates)"]
  d["Implementation<br>(API)"]
  space
  space:3
  e["Data Model<br>(Tables, Entities)"]

a-->b
a-->c
a-->d
c-->e
```

## What Is the Open API Specification

The [OpenAPI Specification](https://www.openapis.org/) (OAS) standardizes how to create an API design document. The typical workflow in an API-First approach using OAS is as follows:

* The team creates a design document and shares it with the involved people for feedback and iterative change.
* When the team and stakeholders agree on the API design, a developer uses the document to generate a server-side skeleton code using a document generator tool.
* Finally, the developer starts working on the API’s business logic using the previously generated code.

[OAS 3.1](https://spec.openapis.org/oas/v3.1.0) allows specifying HTTP resources, verbs, response codes, data models, media types, security schemes, and other API components.

Combining API-Design First with OpenAPI provides some important benefits:

* **Readable by humans and machines**: The YAML/JSON format means it's clear for developers and allows for API design reviews / governance with teams that don't have to read multiple programming languages.
* **Interactive documentation**: API Documentation generators like Bump.sh turn OpenAPI documents into interactive documentation, showing off parameters and examples, so clients can quickly and easily work with the API.
* **Mock servers**: Tools like Microcks and Wiretap can use the API descriptions to simulate the API, allowing parallel development of API and client applications, and allowing feedback to come in early and often.
* **Server-side Validation**: Instead of rewriting all of your validation logic in docs and code, you can use the API descriptions to power your application, making absolute certain the the documentation matches the implementation and reducing time spent writing code.
* **Contract Testing**: Use automated tools to probe your API implementation based off the API descriptions, and add assertions to existing test suites saying "does this response match what it says in the API description", further ensuring the two are in agreement and saving time writing complicated contract testing by hand.
* **Code Generation**: Many tools generate client libraries or server stubs directly from an OpenAPI document, saving loads of time.
* **API Style Guides**: Style guides are hard to enforce against code, developers need to check them manually, but with OpenAPI you can enforce standards on the API from the very first endpoint that is described.

# Example

## Create the API Specification

### Step 1 - Definiting the API Contract

|                         | HTTP verb | Resource       | Error RCs               | Success RCs                   |
|-------------------------|-----------|----------------|-------------------------|-------------------------------|
| get all workouts        | GET       | /workouts      | 404 - Account not found | 200 - Get workout overview    |
| create a new workout    | POST      | /workouts      | 404 - Account not found | 204 - Add workout completed   |
| get a specific workouts | GET       | /workouts/{id} | 404 - Account not found | 200 - Get workout information |

### Step 2 - Top-Level Context of the API

Let’s start by defining the top-level context of the API. To do that, go to the [Swagger Editor](https://editor.swagger.io/) and replace the content on the left side of the editor with the following YAML code:

    openapi: 3.0.3
    info:
      title: Health and Fitness API
      description: API for tracking workouts, nutrition, and health metrics.
      version: 1.0.0
    servers:
      - url: https://myfitnesstracker.ai/api/v1
        description: Demo
    tags:
      - name: fitness
        description: Workouts, nutrition and health metrics

Let’s check each keyword individually:

* openapi – The version of OAS used.
* title – Short title for the API.
* description – Description of the API responsibilities.
* version – The current version of the API, for instance, 1.0-SNAPSHOT.
* servers –  The available machines where the client can access the API.
* tags – A set of unique tags to group API operations in sub-sets.

### Step 3 - Exposing API Paths

Now, let’s create the API endpoints described earlier. To do that, add the following content at the root level of the YAML editor:

    paths:
      /workouts:
        get:
          summary: Get all workouts
          responses:
            '200':
              description: A list of workouts
              content:
                application/json:
                  schema:
                    type: array
                    items:
                      $ref: '#/components/schemas/Workout'
      
        post:
          summary: Create a new workout
          requestBody:
            required: true
            content:
              application/json:
                schema:
                  $ref: '#/components/schemas/Workout'
          responses:
            '201':
              description: Workout created successfully
              content:
                application/json:
                  schema:
                    $ref: '#/components/schemas/Workout'
                    
      /workouts/{id}:
        get:
          summary: Get a specific workout by ID
          parameters:
            - name: id
              in: path
              required: true
              description: ID of the workout
              schema:
                type: string
          responses:
            '200':
              description: A single workout
              content:
                application/json:
                  schema:
                    $ref: '#/components/schemas/Workout'
            '404':
              description: Workout not found

A lot is going on in the document above. Let’s break it down into parts by looking at each keyword individually:

* paths – Defines the API resources /workouts. Under the resources, we must define the get and post verbs available for them.
* summary – Brief information about what the endpoint does.
* description – More detailed information about how the endpoint works.
* operationId – A unique identifier of the operation described.
* requestBody – The request payload containing a description, content, and required keywords.
* responses – A list of all available response codes. Each response code object contains a description and content keywords.
* content - The HTTP Content-Type of the message.

### Step 4 - Defining the Data Model Components

Finally, let’s create the data model objects of our API: the request and response bodies and the error messages. To accomplish that, add the following structure at the root level of the YAML editor:

    components:
      schemas:
        Workout:
          type: object
          properties:
            id:
              type: string
              example: "123"
            type:
              type: string
              example: "Running"
            duration:
              type: integer
              example: 30
              description: Duration in minutes
            caloriesBurned:
              type: integer
              example: 300
            date:
              type: string
              format: date
              example: "2023-10-01"

After adding the content above, all the interpretation errors in the editor should have vanished.

In the above YAML code, we define the same components used in the schema keywords. We can reuse a component as much as we want. Let’s check the keywords in the code individually:

* components – The root level keyword of components.
* schemas – A list of all object definitions.
* type – The type of the field. If we use the object type, we must also define the properties keyword.
* properties – A list of all object field names and their types.

## Create the API Skeleton ##

### Step 5 - Import to Spring Boot Application

This section shows how the developer can import the YAML document into the application and auto-generate the API skeleton code.

First, we must create an empty YAML file named `fitness_api_description.yaml` inside the `/src/main/java/com/fitnesstracker/resources` folder. Then, let’s replace the content of `fitness_api_description.yaml` with the complete YAML code in the Swagger online editor. Finally, we must add the `openapi-generator-maven-plugin` to the `<plugins>` tag in the Spring Boot Application pom.xml file:

    <plugin>
        <groupId>org.openapitools</groupId>
        <artifactId>openapi-generator-maven-plugin</artifactId>
        <version>6.2.1</version>
        <executions>
            <execution>
                <goals>
                    <goal>generate</goal>
                </goals>
                <configuration>
                    <skipValidateSpec>true</skipValidateSpec>
                    <inputSpec>./src/main/java/com/fitnesstracker/resources/fitness_api_description.yaml</inputSpec>
                    <generatorName>spring</generatorName>
        <apiPackage>com.example.demo.api</apiPackage>
        <modelPackage>com.example.demo.model</modelPackage>
        <supportingFilesToGenerate>
            ApiUtil.java
        </supportingFilesToGenerate>
        <configOptions>
            <delegatePattern>true</delegatePattern>
              </configOptions>
                </configuration>
            </execution>
        </executions>
    </plugin>

We need to add the OpenAPI dependencies to generate the API classes:

    <dependency>
        <groupId>org.openapitools</groupId>
        <artifactId>jackson-databind-nullable</artifactId>
        <version>0.2.1</version>
    </dependency>
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-ui</artifactId>
        <version>1.7.0</version>
    </dependency>

### Step 6 - Add OpenAPI and Swagger UI documentation

We can generate also OpenAPI documentation by including an additional dependency:

    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.8.5</version>
    </dependency>

Now, let’s generate the server-side code from the YAML file by running

    mvn clean install

After that, we can check the following generated code in the target folder:

![Target folder](resources/targetfolder.png?raw=true)

## Trying it out in the browser

We can run our application and find the OpenAPI descriptions at 

    /v3/api-docs

We can also get the documentation as a yaml file by changing the path to 

    /v3/api-docs.yaml

The springdoc-openapi dependency already includes the Swagger UI, so we’re all set to access the API documentation at:

    /swagger-ui/index.html

![Target folder](resources/swaggerui.png?raw=true)

## Trying it out with Postman

Let's call the API from Postman.

As described on [GitHub Docs](https://docs.github.com/en/codespaces/developing-in-a-codespace/forwarding-ports-in-your-codespace), we need the address and the Github Token. The address is easy: copy it from the port forwarding:

![Target folder](resources/port.png?raw=true)

The Github Token we can get in the codespace terminal by entering `echo $GITHUB_TOKEN`. The token is a string beginning with `ghu_`.

Let's enter the address and token into Postman. By calling the base URL we get the website. 

![Target folder](resources/postman_website.png?raw=true)

To query the API, we have to extend the address with the API's path definition: `/api/v1/workouts`. Let's put a breakpoint in the method we will be calling:

![Target folder](resources/breakpoint.png?raw=true)

When calling the API method, we hit the breakpoint. However, because we do not have implemented the method we get an error code back in Postman:

![Target folder](resources/postman_api.png?raw=true)

## Create a REST Client ##

Now that we have the API and its documentation, we can generate a REST client that consumes the API.

OpenAPI provides a utility jar that allows us to generate REST clients for various programming languages and multiple frameworks. We can install it with npm:

    npm install @openapitools/openapi-generator-cli -g

Next, let's generate the client code:

    openapi-generator-cli generate -i webapp/src/main/java/com/fitnesstracker/resources/fitness_api_description.yaml -g java -p java8=true --library resttemplate -o client --api-package com.fitnesstracker.client.api --model-package com.fitnesstracker.client.model --invoker-package com.fitnesstracker.client.invoker --group-id com.fitnesstracker --artifact-id client.fitnesstracker --artifact-version 0.0.1-SNAPSHOT

This generates the API and model along with a JAR file that we can add as a dependency into a spring boot application:

![Target folder](resources/client.png?raw=true)

