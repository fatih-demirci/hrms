import axios from 'axios'
import React from 'react'

export default class TypeOfWorkService {
    getAll(){
        return axios.get("http://localhost:8080/api/TypeOfWork/getAll")
    }
}
