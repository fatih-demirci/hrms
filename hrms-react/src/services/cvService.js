import axios from "axios";
import { toast } from "react-toastify";

export default class CvService{

    getCv(id){
        return axios.post("http://localhost:8080/api/jobseeker/getCv?jobSeekerId="+id)
    }

    addCv(jobSeekerId, cv){
        return axios.post("http://localhost:8080/api/jobseeker/addCv?jobSeekerId="+jobSeekerId,cv).then(function (response) {
          
            console.log(response.data.message)
            toast.success(response.data.message)
         })
         .catch(function (error) {
             toast.error(error.response.data)
         return error.response.data;
         });
    
    }

    getAll(){
        return axios.get("http://localhost:8080/api/Cv/getAll")
    }

    getAllCvWithJobSeeker(){
        return axios.get("http://localhost:8080/api/Cv/cvWithJobSeekerDetails")
    }
}