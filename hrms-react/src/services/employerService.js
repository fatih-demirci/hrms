import axios from "axios";
import { Label } from "semantic-ui-react";
import { toast } from "react-toastify";
export default class EmployerService {

    getEmployers() {
        return axios.get("http://localhost:8080/api/employer/getall")
    }

     addEmployer (employer) {
        let result = axios.post("http://localhost:8080/api/employer/add",employer).then(function (response) {
          
           console.log(response.data.message)
           toast.success(response.data.message)
        })
        .catch(function (error) {
            toast.success(error.response.data)
            console.log(error.response.data)
        return error.response.data;
        });
            
    }}

