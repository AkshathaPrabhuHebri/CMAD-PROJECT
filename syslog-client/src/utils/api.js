import axios from 'axios';
class API{
    static SERVER="http://54.245.136.98:5100"
    static authenticateURI=API.SERVER+"/authenticate";
    static userURI=API.SERVER+"/user";


    static authenticate(username,password){
        return axios.post(API.authenticateURI, {
            username: username,
            password: password
        })
    }

    static addUser(username,password,devices){
        let authToken=localStorage.getItem("authToken");
        return axios.post(API.userURI, {
            username: username,
            password: password,
            devices:devices
        },{
            headers: {
                'Authorization': 'Bearer '+authToken,
            }
        })
    }

    static getUsers(){
        let authToken=localStorage.getItem("authToken");
        return axios.get(API.userURI,{
            headers: {
                'Authorization': 'Bearer '+authToken,
            }
        })
    }
}
export default API;
