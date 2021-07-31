import axios from "axios";
import { toast } from "react-toastify";
export default class JobSeekerService{
    getJobseekers(){
        return axios.get("http://localhost:8080/api/jobseeker/getall")
    }

    addJobSeeker(jobSeeker){
        return axios.post("http://localhost:8080/api/jobseeker/add",jobSeeker).then(function (response) {
          
            console.log(response.data.message)
          
         })
         .catch(function (error) {
            toast.success(error.response.data)
         return error.response.data;
         });
    }

    getJobSeekerById(id){
        return axios.get(`http://localhost:8080/api/jobseeker/getJobSeekerById?id=${id}`)
    }
}