import { Formik, Form } from 'formik'
import React, { useState, useEffect } from 'react'
import { render } from 'react-dom'
import { Link, NavLink, Route, Switch, useHistory, useParams } from 'react-router-dom'
import { Button, Card, Container, FormField, Grid, Pagination, Menu, CardContent } from 'semantic-ui-react'
import AdvertisementService from '../services/advertisementService'
import * as Yup from 'yup';
import CityService from '../services/cityService'
import TypeOfWorkService from '../services/typeOfWorkService'
export default function Advertisement() {
  const history = useHistory()
  const [advertisements, setAdvertisements] = useState([])
  const [advertisementsCount, setAdvertisementsCount] = useState([])
  const [citys, setCitys] = useState([])
  const [typeOfWorks, setTypeOfWorks] = useState([])
  let [count,setCount] = useState(100)

  let { page } = useParams()

  let [activePage, setActivePage] = useState(page != null ? page : 1)
  let [pageSize, setPageSize] = useState(10)
  let [pageSizeValue, setPageSizeValue] = useState(pageSize)
  let [totalPages, setTotalPages] = useState(calcTotalPages())

  let advertisementService = new AdvertisementService()
  let cityService = new CityService()
  let typeOfWorkService = new TypeOfWorkService()

  let [city, setCity] = useState("*")
  let [typeOfWork, setTypeOfWork] = useState("*")

  useEffect(() => {

    advertisementService.getEmployerWithAdvertisementDetailsByCityAndTypeOfWork(true, city, true, activePage, pageSize, typeOfWork).then(result => setAdvertisements(result.data.data))
    advertisementService.countByIsOpenAndApprovedAndCityAndTypeOfWork(true, city, true, typeOfWork).then(result => setCount(result.data))
    cityService.getAll().then(result => setCitys(result.data.data))
    typeOfWorkService.getAll().then(result=> setTypeOfWorks(result.data.data))

    setTotalPages(calcTotalPages())

  }, [pageSize, activePage, totalPages, city, typeOfWork,count])


  function formatDate(date) {
    date = String(date).split('T');
    return date[0]
  }

  function handlePageNumber(e, { activePage }) {
    setActivePage(activePage)
  }

  function handlePageSize() {
    { pageSizeValue != "" ? setPageSize(pageSizeValue) : setActivePage(1) }
    setActivePage(1)
  }

  function handlePageSizeValue() {
    setPageSizeValue(document.getElementById("PageSizeInput").value)
  }

  function calcTotalPages() {
    return (count % pageSize === 0 ? count / pageSize : parseInt(count / pageSize) + 1)
  }

  const initialValuesCity = {
    city: "İl Seçiniz"
  }
  const schemaCity = Yup.object({
    city: Yup.string()
  })


  return (
    <div>

      <Formik
        initialValues={initialValuesCity}
        validationSchema={schemaCity}
        onSubmit={(values) => {
          values.city === "İl Seçiniz" ?
           setCity("*")
            :
          setCity(values.city)

          values.typeOfWork==="Çalışma Türünü Seçiniz" ?
          setTypeOfWork("*")
          :
          setTypeOfWork(values.typeOfWork)

          setActivePage(1)
        }}
      >
        {formik => (
          <Form className="ui form">

            <Menu>
              <Menu.Item header>Filtrele</Menu.Item>

              <FormField name="city" control="select" onChange={formik.handleChange} style={{marginTop:12,marginLeft:5 }} >
                <option key={0} value="İl Seçiniz">İl Seçiniz</option>
                {citys.map(city => (<option key={city.id} value={city.city}>{city.city}</option>))}
              </FormField>
              <FormField name="typeOfWork" control="select" onChange={formik.handleChange} style={{marginTop:12,marginLeft:10 }} >
                <option key={0} value="Çalışma Türünü Seçiniz">Çalışma Türünü Seçiniz</option>
                {typeOfWorks.map(typeOfWork => (<option key={typeOfWork.typeOfWork} value={typeOfWork.typeOfWork}>{typeOfWork.typeOfWork}</option>))}
              </FormField>
              <Button color="green" type="submit" style={{marginTop:13,marginBottom:13,marginLeft:15}} >Filtrele</Button>

            </Menu>

          </Form>
        )}

      </Formik>

      <Container className="main" >
        <Grid>
          {

            advertisements?.map(advertisement => (

              <Card key={advertisement.id} centered raised as={NavLink} to={`/AdvertisementList/${advertisement.id}`}
                header={advertisement.companyName}
                meta={formatDate(advertisement.releaseDate) + " - " + formatDate(advertisement.applicationDeadline)}
                description={advertisement.positionName}
                extra={"Açık Pozisyon: "+advertisement.openPositions+"/ Şehir: "+advertisement.city+"/ Çalışma Türü: "+advertisement.typeOfWork}
               
                
              
              />
              
            )
            
            )

          }
      
        </Grid>
        <div style={{ marginTop: 30 }}>

          {count > 0 ?
            <Pagination id="pagination" totalPages={calcTotalPages()} onPageChange={handlePageNumber} activePage={activePage} />
            :
            null
          }

          {
            count > 0 ?
              <form >
                <label>
                  <input id="PageSizeInput" type="number" defaultValue={pageSize} onChange={handlePageSizeValue} placeholder="Sayfa başına iş ilanı" />
                </label>
                <input type="button" value="Onayla" onClick={handlePageSize} ></input>
              </form>
              : null
          }

        </div>

      </Container>
    </div>

  )

}
