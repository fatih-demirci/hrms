import axios from "axios"
import { toast } from "react-toastify";

export default class CompanyNameUpdateService{

companyNameUpdate(employerId,companyNameUpdate){
    return axios.post(`http://localhost:8080/update?companyNameUpdate=${companyNameUpdate}&employerId=${employerId}`).then(function (response) {

        console.log(response.data.message)
        toast.success(response.data.message)
    })
        .catch(function (error) {
            toast.success(error.response.data)
            return error.response.data;
        });
}

approve(employerId){
    return axios.post(`http://localhost:8080/approve?employerId=${employerId}`).then(function (response) {

        console.log(response.data.message)
        toast.success(response.data.message)
    })
        .catch(function (error) {
            toast.success(error.response.data)
            return error.response.data;
        });
}

}