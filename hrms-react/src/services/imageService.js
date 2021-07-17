import axios from "axios";
import { toast } from "react-toastify";
export default class ImageService {
    uploadImage(jobSeekerId, image){
        axios.post("http://localhost:8080/api/image/uploadImage?jobSeekerId="+jobSeekerId,image).then(function (response) {
            console.log(response.data.message)
            console.log(response)
            toast.success(response.data.message)
            
         })
         .catch(function (error) {
     
             console.log(error.response.data)
       //  return error.response.data;
         });
    }
}