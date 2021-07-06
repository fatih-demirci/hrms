import axios from "axios";

export default class JobSeekerService{
    getJobseekers(){
        return axios.get("http://localhost:8080/api/jobseeker/getall")
    }

    addJobSeeker(jobSeeker){
        return axios.post("http://localhost:8080/api/jobseeker",jobSeeker)
    }
}