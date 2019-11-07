
const app = new Vue({
    el:'#app',
    data:{
        texto:'hola mundo',
        firstName: '',
        lastName: '',
        username: '',
        password: '',
        respuesta: false,
        textoRespuesta:'',
        fondoRespuesta: '',
    },
    methods:{
        postREST:function(){
            fetch('http://'+ 'localhost' +':4000/sa-auth-ms/resources/users', {
                credentials: "omit",
                headers: {
                  Accept: 'application/json',
                  'Content-Type': 'application/json',
                },
                referrer: "http://localhost:4000/sa-auth-ms/resources/users",
                body: JSON.stringify({
                    firstName: this.firstName,
                    lastName: this.lastName,
                    username: this.username,
                    password: this.password
                }),
                method: 'POST',
                mode: "cors"
            }).then(response => {
                if(response.ok) {
                    this.respuesta=true
                    this.textoRespuesta = 'Usuario creado satisfactoriamente desde Microservicio: ['+this.firstName+', '+this.lastName+', '+this.username+', '+this.password+ ']'
                    this.fondoRespuesta="p-3 mb-2 bg-success text-white"
                } else {
                    this.respuesta=true
                    this.textoRespuesta = 'Error creando el usuario'
                    this.fondoRespuesta="p-3 mb-2 bg-danger text-white"
                }
             }).catch(error =>{
                this.respuesta=true
                this.textoRespuesta = 'Error creando el usuario'
                this.fondoRespuesta="p-3 mb-2 bg-danger text-white"
             })

        },
        postGraphQL:function(){
            console.log(`mutation {
                createUser(user: {
                  firstName:" `+this.firstName+`"
                  lastName: "`+this.lastName+`"
                  username: "`+this.username+`"
                  password: "`+this.password+`"
                }) {
                  username
                }
              }`)
            fetch("http://localhost/graphql?", {
                credentials: "omit",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                referrer: "http://localhost:5000/graphiql",
                body: JSON.stringify({
                    query:`mutation {
                        createUser(user: {
                          firstName:" `+this.firstName+`"
                          lastName: "`+this.lastName+`"
                          username: "`+this.username+`"
                          password: "`+this.password+`"
                        }) {
                          username
                        }
                      }`
                }),
                method: "POST",
                mode: "cors"
            }).then(response => {
                if(response.ok) {
                    this.respuesta=true
                    this.textoRespuesta = 'Usuario creado satisfactoriamente desde API Gateway: ['+this.firstName+', '+this.lastName+', '+this.username+', '+this.password+ ']'
                    this.fondoRespuesta="p-3 mb-2 bg-success text-white"
                } else {
                    this.respuesta=true
                    this.textoRespuesta = 'Error creando el usuario'
                    this.fondoRespuesta="p-3 mb-2 bg-danger text-white"
                }
             }).catch(error =>{
                this.respuesta=true
                this.textoRespuesta = 'Error creando el usuario'
                this.fondoRespuesta="p-3 mb-2 bg-danger text-white"
             })


        }
    }
})
