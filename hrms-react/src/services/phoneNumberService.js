import axios from "axios";
import { toast } from "react-toastify";
export default class PhoneNumberService{

   isUpdate(employerId,exPhoneNumberId){
       return axios.get(`http://localhost:8080/PhoneNumber/isUpdate?employerId=${employerId}&exPhoneNumberId=${exPhoneNumberId}`)
   }

   updatePhoneNumber(employerId,phoneNumber){
       return axios.post(`http://localhost:8080/api/employer/updatePhoneNumber?employerId=${employerId}`,phoneNumber).then(function (response) {
          
        console.log(response.data.message)
        toast.success(response.data.message)
     })
     .catch(function (error) {
         toast.success(error.response.data)
         console.log(error.response.data)
     return error.response.data;
     });
   }

   approve(employerId,phoneNumberId){
        return axios.post(`http://localhost:8080/PhoneNumber/approve?employerId=${employerId}&phoneNumberId=${phoneNumberId}`).then(function (response) {
          
            console.log(response.data.message)
            toast.success(response.data.message)
         })
         .catch(function (error) {
             toast.success(error.response.data)
             console.log(error.response.data)
         return error.response.data;
   })
   }
   
}