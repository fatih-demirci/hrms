import axios from "axios";

export default class JobPositionService {
    getJobPositions() {
        return axios.get("http://localhost:8080/api/jobposition/getall")
    }

    addJobPosition(jobPosition) {
        return axios.post("http://localhost:8080/api/jobposition",jobPosition)
    }
}