# Introduction #

This is meant to be a simple project demonstrating how to create a Spring service, running in an http container, using Apache Avro to handle requests and responses.

The project uses ant and ivy to build.  After checking it out, you can run an `ant dist` to build everything and the artifacts will show up in the dist directory.

# The Code #

## Service ##
Avro requires a schema definition file to know what data types to create.  This file is tanzaniabusservice.avpr and can be found in the project root.  It defines a single Company type.  The same file also defines the methods to add to the generated interface.  There are 2 methods defined: loadCompany and listCompaniesAboveRating.

Avro will take this schema file and generate Java classes to be used in the rest of the service code.  This is done in the rebuild-avro-gen ant task in the build.xml file.

Once the classes are generated, the interface can be implemented.  This can be seen in the AvroBusService class.  For this example the service just calls a simple dao, which returns test data.

The purpose of this is to show how to expose this service over http and that can be seen in the TanzaniaBusServiceServlet class.  Running an Avro service over http is simple and only requires you to extend the ResponderServlet class.  The service class is bound in the constructor and Avro handles requests and calls into the service class, serializing and deserializing along the way.

The example makes use of Spring for dependency injection, which requires a bit of less-than-ideal code to get going.  That code is in the AvroBusServiceProvider class.

## Client ##
The sample client in the SampleClient class just makes a few class to the service to prove that it works.  The code is simple enough to not require any explaination.