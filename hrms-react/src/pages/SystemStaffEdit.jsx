import React,{useState,useEffect} from 'react'
import SystemStaffService from '../services/systemStaffService'
import * as Yup from 'yup';
import { Form, Formik } from 'formik';
import HrmsTextInput from '../utilities/customFormControls/HrmsTextInput';
import { Button } from 'semantic-ui-react';
import Condition from 'yup/lib/Condition';

export default function SystemStaffEdit() {

    let systemStaffService = new SystemStaffService()
    let [systemStaff,setSystemStaff] = useState(null)

    useEffect(()=>(
        systemStaffService.getSystemStaff(2).then(result=>setSystemStaff(result.data.data))
    ),[])

   const schema=Yup.object({
    name: Yup.string().required("İsim gerekli"),
    lastName: Yup.string().required("Soyad gerekli"),
    email: Yup.string().email("Geçersiz e-mail adresi").required("E-mail adresi gerekli"),
    password: Yup.string().min(6, "Şifre çok kısa").required("Şifre gerekli"),
    passwordRepeat: Yup.string().oneOf([Yup.ref("password"),null],"Şifre ve şifre tekrar uyuşmuyor").required("Şifre tekrar gerekli")
})
    console.log(systemStaff)

    return (
        <div>
            {systemStaff!==null?
            <Formik
                initialValues={{
                    name: systemStaff.name,
                    lastName: systemStaff.lastName,
                    email: systemStaff.member.email,
                    password: systemStaff.member.password,
                    passwordRepeat: systemStaff.member.passwordRepeat
                }}
                validationSchema={schema}
                onSubmit={values => {
                    const updateSystemStaff = {
                        id:systemStaff.id,
                        name:values.name,
                        lastName:values.lastName,
                        member:{
                        email:values.email,
                        password:values.password,
                        passwordRepeat:values.passwordRepeat
                        }
                    }
                    systemStaffService.add(updateSystemStaff)
                }}
            >
                <Form className="ui form">
                    <HrmsTextInput name="name" placeholder="Ad"></HrmsTextInput>
                    <HrmsTextInput name="lastName" placeholder="Soyad"></HrmsTextInput>
                    <HrmsTextInput name="email" placeholder="e-mail adresi"></HrmsTextInput>
                    <HrmsTextInput name="password" placeholder="Şifre" type="password"></HrmsTextInput>
                    <HrmsTextInput name="passwordRepeat" placeholder="Şifre tekrar" type="password"></HrmsTextInput>
                    <Button color="green" type="submit">Onayla</Button>
                </Form>
            </Formik>
            :null}
        </div>
    )
}
