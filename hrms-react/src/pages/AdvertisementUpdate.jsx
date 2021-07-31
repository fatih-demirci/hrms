import { Form, Formik } from 'formik'
import React, { useEffect, useState } from 'react'
import { Button, FormCheckbox, FormField, Label } from 'semantic-ui-react'
import HrmsTextInput from '../utilities/customFormControls/HrmsTextInput'
import * as Yup from 'yup'
import EmployerService from '../services/employerService'
import CityService from '../services/cityService'
import JobPositionService from '../services/jobPositionService'
import TypeOfWorkService from '../services/typeOfWorkService'
import WorkingTimeService from '../services/workingTimeService'
import AdvertisementService from '../services/advertisementService'
export default function AdvertisementUpdate() {
    let advertisementService = new AdvertisementService()
    let employerService = new EmployerService()
    const [jobAdvertisements, setJobAdvertisements] = useState([])
    const [cities, setCities] = useState([])
    const [jobPositions, setJobPositions] = useState([])
    const [workingTimes, setworkingTimes] = useState([])

   

    useEffect(() => {
        
        let cityService = new CityService()
        cityService.getAll().then((result) => setCities(result.data.data))
    }, [])

    useEffect(() => {
        let jobPositionService = new JobPositionService()
        jobPositionService.getJobPositions().then((result) => setJobPositions(result.data.data))
    }, [])

    const [typeOfWorks, setTypeOfWorks] = useState([])

    useEffect(() => {
        let typeOfWorkService = new TypeOfWorkService()
        typeOfWorkService.getAll().then((result) => setTypeOfWorks(result.data.data))
    }, [])

    useEffect(() => {
        let workingTimeService = new WorkingTimeService()
        workingTimeService.getAll().then((result) => setworkingTimes(result.data.data))
    }, [])

    useEffect(()=>{
        employerService.getAllEmployerJobAdvertisements(149).then(result => setJobAdvertisements(result.data.data.jobAdvertisements)) 
    },[])

    

    function formatDate(date) {
        date = String(date).split('T');
        return date[0]
      }
   
    return (
        <div>
            {jobAdvertisements.map(jobAdvertisement => (
            
                <Formik key={jobAdvertisement.id}
                    initialValues={{
                        applicationDeadline: formatDate(jobAdvertisement.applicationDeadline), cityId: jobAdvertisement.city.id,
                        jobAdvertisementOpen: jobAdvertisement.jobAdvertisementOpen, jobDescription: jobAdvertisement.jobDescription,
                        jobPositionId: jobAdvertisement.jobPosition.id, minSalary: jobAdvertisement.minSalary, maxSalary: jobAdvertisement.maxSalary,
                        openPositions: jobAdvertisement.openPositions, typeOfWorkId: jobAdvertisement.typeOfWork.id, workingTimeId: jobAdvertisement.workingTime.id
                    }}
                    validationSchema={Yup.object({
                        applicationDeadline: Yup.date().required("İlan son başvuru tarihi zorunlu"),
                        cityId: Yup.number().required("Şehir seçimi zorunlu"),
                        jobAdvertisementOpen: Yup.bool().required("İş ilanı aktifliği zorunlu"),
                        jobDescription: Yup.string().required("İş açıklaması zorunlu"),
                        jobPositionId: Yup.number().required("İş pozisyonu zorunlu"),
                        minSalary: Yup.number(),
                        maxSalary: Yup.number(),
                        openPositions: Yup.number().required("Açık pozisyon sayısı zorunlu"),
                        typeOfWorkId: Yup.number().required("Çalışma türü zorunlu"),
                        workingTimeId: Yup.number().required("Çalışma zamanı zorunlu")
                    })}
                    onSubmit={(values) => {

                        const jobAdvertisementUpdate = {
                            applicationDeadline: values.applicationDeadline,
                            city: { id: values.cityId },
                            jobAdvertisementOpen: values.jobAdvertisementOpen,
                            jobDescription: values.jobDescription,
                            jobPosition: { id: values.jobPositionId },
                            maxSalary: values.maxSalary,
                            minSalary: values.minSalary,
                            openPositions: values.openPositions,
                            typeOfWork: { id: values.typeOfWorkId },
                            workingTime: { id: values.workingTimeId },
                            exJobAdvertisementId:jobAdvertisement.id
                        }

                        advertisementService.addAdvertisement(jobAdvertisementUpdate, 149) 


                    }}

                >
                    {formik => (

                        <Form className="ui form">

                            <Label>{jobAdvertisement.exJobAdvertisementId!==0?"Güncelleme Onayı Bekliyor":jobAdvertisement.approved===true?"Onaylı İlan":"Onay Bekliyor"}</Label>

                            <HrmsTextInput name="applicationDeadline" placeholder="Son başvuru tarihi" type="date"></HrmsTextInput>

                            <FormField name="cityId" control="select" onChange={formik.handleChange} defaultValue={jobAdvertisement.city.id}>
                                {cities.map(city => (<option key={city.id} value={city.id}>{city.city}</option>))}
                            </FormField>

                            <FormCheckbox id={"0"} name="jobAdvertisementOpen" label="İş İlanı Aktif" onChange={formik.handleChange} checked={jobAdvertisement.jobAdvertisementOpen}></FormCheckbox>

                            <HrmsTextInput name="jobDescription" placeholder="İş açıklaması" type="text"></HrmsTextInput>

                            <FormField name="jobPositionId" control="select" onChange={formik.handleChange} defaultValue={jobAdvertisement.jobPosition.id}>
                                {jobPositions.map(jobPosition => (<option key={jobPosition.id} value={jobPosition.id}>{jobPosition.name}</option>))}
                            </FormField>

                            <HrmsTextInput name="minSalary" placeholder="Taban maaş" type="number"></HrmsTextInput>

                            <HrmsTextInput name="maxSalary" placeholder="Tavan maaş" type="number"></HrmsTextInput>

                            <HrmsTextInput name="openPositions" placeholder="Açık pozisyon sayısı" type="number"></HrmsTextInput>

                            <FormField name="typeOfWorkId" control="select" onChange={formik.handleChange} defaultValue={jobAdvertisement.typeOfWork.id}>
                                {typeOfWorks.map(typeOfWork => (<option key={typeOfWork.id} value={typeOfWork.id}>{typeOfWork.typeOfWork}</option>))}
                            </FormField>

                            <FormField name="workingTimeId" control="select" onChange={formik.handleChange} defaultValue={jobAdvertisement.workingTime.id}>
                                {workingTimes.map(workingTime => (<option key={workingTime.id} value={workingTime.id}>{workingTime.workingTime}</option>))}
                            </FormField>

                            <Button color="green" type="submit">İş İlanını Güncelle</Button>

                        </Form>

                    )}


                </Formik>

            ))}

        </div>
    )
}
