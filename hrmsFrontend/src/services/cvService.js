import axios from "axios";

export default class CvService{

    getCv(id){
        return axios.post("http://localhost:8080/api/jobseeker/getCv?jobSeekerId="+id)
    }

    addCv(jobSeekerId, cv){
        return axios.post("http://localhost:8080/api/jobseeker/addCv?jobSeekerId="+jobSeekerId,cv)    }
}