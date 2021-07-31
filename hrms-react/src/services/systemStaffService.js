import axios from "axios";
import { toast } from "react-toastify";

export default class SystemStaffService{
    add(systemStaff){
        let result = axios.post("http://localhost:8080/SystemStaff/add",systemStaff).then(function (response) {
          
            console.log(response.data.message)
            toast.success(response.data.message)
         })
         .catch(function (error) {
             toast.success(error.response.data)
             console.log(error.response.data)
         return error.response.data;
         });
    }

    getSystemStaff(id){
        return axios.get(`http://localhost:8080/SystemStaff/getSystemStaff?id=${id}`)
    }
}