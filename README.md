## How to RUN

  - Assuming that **NodeJS** already installed, if not please install 
  - Open a terminal
  - Write **"npm install -g json-server"** 
  - Create a file which name is **db.json**
  - Put the following JSON inside the **db.json** 
  
      ```yaml
          {
           "data": [
           {
              "id": 1,
              "name": "apple",
              "price": "3",
              "stock":100
              },
        {
              "id": 2,
              "name": "grapes",
              "price": "5",
              "stock":50
              }
           ]
        }
        ```
  - Open a terminal where you keep the **db.json** file
  - Write **"json-server --watch db.json"**
  - Check the **http://localhost:3000/posts/** via any browser or Postman
