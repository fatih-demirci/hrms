import { Form, Formik } from 'formik';
import React, { useState, useEffect } from 'react'
import { Button, Dropdown, DropdownItem, DropdownMenu, FormCheckbox, FormField, Select } from 'semantic-ui-react';
import * as Yup from 'yup';
import AdvertisementService from '../services/advertisementService';
import HrmsTextInput from '../utilities/customFormControls/HrmsTextInput';
import CityService from '../services/cityService';
import JobPositionService from '../services/jobPositionService';
import TypeOfWorkService from '../services/typeOfWorkService';
import WorkingTimeService from '../services/workingTimeService';
export default function JobAdvertisementAdd() {

    let advertisementService = new AdvertisementService()

    const initialValues = {
        applicationDeadline: "", cityId: 1,
        jobAdvertisementOpen: false, jobDescription: "",
        jobPositionId: 1, minSalary: "", maxSalary: "",
        openPositions: "", typeOfWorkId: 1, workingTimeId: 1
    }

    const schema = Yup.object({
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
    })

    const [cities, setCities] = useState([])

    useEffect(() => {
        let cityService = new CityService()
        cityService.getAll().then((result) => setCities(result.data.data))
    }, [])

    const [jobPositions, setJobPositions] = useState([])

    useEffect(() => {
        let jobPositionService = new JobPositionService()
        jobPositionService.getJobPositions().then((result) => setJobPositions(result.data.data))
    }, [])

    const [typeOfWorks, setTypeOfWorks] = useState([])

    useEffect(() => {
        let typeOfWorkService = new TypeOfWorkService()
        typeOfWorkService.getAll().then((result) => setTypeOfWorks(result.data.data))
    }, [])

    const [workingTimes, setworkingTimes] = useState([])

    useEffect(() => {
        let workingTimeService = new WorkingTimeService()
        workingTimeService.getAll().then((result) => setworkingTimes(result.data.data))
    }, [])

    return (

        <div>

            <Formik
                initialValues={initialValues}
                validationSchema={schema}
                onSubmit={(values) => {

                   const jobAdvertisement={
                        applicationDeadline:values.applicationDeadline,
                        city:{id:values.cityId},
                        jobAdvertisementOpen:values.jobAdvertisementOpen,
                        jobDescription:values.jobDescription,
                        jobPosition:{id:values.jobPositionId},
                        maxSalary:values.maxSalary,
                        minSalary:values.minSalary,
                        openPositions:values.openPositions,
                        typeOfWork:{id:values.typeOfWorkId},
                        workingTime:{id:values.workingTimeId}
                    }

                    advertisementService.addAdvertisement(jobAdvertisement,149)

                }}

            >
                {formik => (

                    <Form className="ui form">

                        <HrmsTextInput name="applicationDeadline" placeholder="Son başvuru tarihi" type="date"></HrmsTextInput>

                        <FormField name="cityId" control="select" onChange={formik.handleChange}>
                            {cities.map(city => (<option key={city.id} value={city.id}>{city.city}</option> )) }
                        </FormField>

                        <FormCheckbox id={"0"} name="jobAdvertisementOpen" label="İş İlanı Aktif" onChange={formik.handleChange}></FormCheckbox>

                        <HrmsTextInput name="jobDescription" placeholder="İş açıklaması" type="text"></HrmsTextInput>

                        <FormField name="jobPositionId" control="select" onChange={formik.handleChange}>
                            {jobPositions.map(jobPosition => (<option key={jobPosition.id} value={jobPosition.id}>{jobPosition.name}</option>))}
                        </FormField>
                        
                        <HrmsTextInput name="minSalary" placeholder="Taban maaş" type="number"></HrmsTextInput>

                        <HrmsTextInput name="maxSalary" placeholder="Tavan maaş" type="number"></HrmsTextInput>

                        <HrmsTextInput name="openPositions" placeholder="Açık pozisyon sayısı" type="number"></HrmsTextInput>

                        <FormField name="typeOfWorkId" control="select" onChange={formik.handleChange}>
                            { typeOfWorks.map(typeOfWork => (<option key={typeOfWork.id} value={typeOfWork.id}>{typeOfWork.typeOfWork}</option>))}
                        </FormField>

                        <FormField name="workingTimeId" control="select" onChange={formik.handleChange}>
                            {workingTimes.map(workingTime => (<option key={workingTime.id} value={workingTime.id}>{workingTime.workingTime}</option>)) }
                        </FormField>

                        <Button color="green" type="submit">İş ilanı ekle</Button>

                    </Form>

                )}


            </Formik>
        </div>
    )
}
