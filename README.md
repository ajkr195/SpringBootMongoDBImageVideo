# SpringBootMongoDBImageVideo
SpringBoot MongoDB Uploading Image and Video Files

# Some useful MongoDB Commands

<h3>List MongoDB Databases: </h3>

```
list dbs

```

<h3>Drop existing database:</h3>

```
use somedatabase
db.dropDatabase()

```

<h3>Create new database:</h3>

```
use appusersdb

```

<h3>Create Collection:</h3>

```
db.createCollection('appusers')

```

<h3>Insert one record into collection:</h3>

```
db.appusersdb.insert({
username: 'user1',
firstname: 'firstname1',
lastname: 'lastname1',
address: 'address1',
roles: ['adminuser','administrator'],
status: {
    createdby: 'adminuser',
    status: 'active'
},
createdate: Date()
})

```

<h3>Insert many records into collection:</h3>

```
db.appusersdb.insertMany([
 
{
username: 'user2',
firstname: 'firstname2',
lastname: 'lastname2',
address: 'address2',
roles: ['adminuser','administrator'],
status: {
    createdby: 'adminuser',
    status: 'active'
},
createdate: Date()
},
{
username: 'user3',
firstname: 'firstname3',
lastname: 'lastname3',
address: 'address3',
roles: ['adminuser','administrator'],
status: {
    createdby: 'adminuser',
    status: 'active'
},
createdate: Date()
},
{
username: 'user4',
firstname: 'firstname4',
lastname: 'lastname4',
address: 'address4',
roles: ['adminuser','administrator'],
status: {
    createdby: 'adminuser',
    status: 'active'
},
createdate: Date()
}
])

```




<h3>Find all records in Database - "Pretty" Formatted: </h3>

```
db.appusersdb.find().pretty()
```

<h3>Find a user with specific first name in the database - "Pretty" Formatted:  </h3>

```
db.appusersdb.find({firstname: 'firstname1'}).pretty()
```


<h3>Find user with first name sorted ascending (alphabetically):  </h3>

```
db.appusersdb.find().sort({firstname: 1}).pretty()
```

<h3>Find user with first name sorted descending (alphabetically):  </h3>

```
db.appusersdb.find().sort({firstname: -1}).pretty()
```

<h3>Limit search to x (2 in this case) users, sort them descending (alphabetically):  </h3>

```
db.appusersdb.find().limit(2).sort({firstname: 1}).pretty()
```


<h3>For all records in Database - For EACH: </h3>

```
db.appusersdb.find().forEach(function(mydoc) {
  print("First Name: " + mydoc.firstname)
})
```


<h3>Get only one record from Database: </h3>

```
db.appusersdb.findOne({roles: 'adminuser'
})
```


