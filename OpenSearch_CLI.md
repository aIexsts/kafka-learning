# OpenSearch CLI

http://localhost:5601/app/dev_tools#/console

https://bonsai.io/

## Basic

Get information on OpenSearch
 ```
GET /
 ```

Create index (Table)
 ```
PUT /my-first-index
 ```

Add some data to index (Create Row)
 ```
PUT /my-first-index/_doc/1
{
  "Description": "Test"
}
 ```

Retrieve data
 ```
GET /my-first-index/_doc/1
 ```

Delete data
 ```
DELETE /my-first-index/_doc/1
 ```

Delete index
 ```
DELETE /my-first-index
 ```
