import axios from "axios";
import { toast } from "react-toastify";

export default class EmailUpdateService{
   EmailUpdate(employerId,email){
       return axios.post(`http://localhost:8080/EmailUpdate/update?emailUpdate=${email}&employerId=${employerId}`).then(function (response) {

        console.log(response.data.message)
        toast.success(response.data.message)
    })
        .catch(function (error) {
            toast.success(error.response.data)
            return error.response.data;
        });
   }

  approve(employerId){
      return axios.post(`http://localhost:8080/EmailUpdate/approve?employerId=${employerId}`).then(function (response) {

        console.log(response.data.message)
        toast.success(response.data.message)
    })
        .catch(function (error) {
            toast.success(error.response.data)
            return error.response.data;
        });
  }
}