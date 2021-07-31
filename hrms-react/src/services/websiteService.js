import axios from "axios";
import { toast } from "react-toastify";
export default class WebsiteService{

   websiteIsUpdate(id){
    return axios.get(`http://localhost:8080/WebsiteController/isUpdate?id=${id}`)
   }

   updateWebsite(employerId,website){
       return axios.post(`http://localhost:8080/api/employer/updateWebsite?employerId=${employerId}`,website).then(function (response) {
          
        console.log(response.data.message)
        toast.success(response.data.message)
     })
     .catch(function (error) {
         toast.success(error.response.data)
         console.log(error.response.data)
     return error.response.data;
     });
   }

   approve(employerId,websiteId){
       return axios.post(`http://localhost:8080/WebsiteController/approve?employerId=${employerId}&websiteId=${websiteId}`).then(function (response) {
          
        console.log(response.data.message)
        toast.success(response.data.message)
     })
     .catch(function (error) {
         toast.success(error.response.data)
         console.log(error.response.data)
     return error.response.data;
     });
   }
   
   
}