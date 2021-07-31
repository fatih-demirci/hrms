import React, { useState } from 'react'
import {NavLink, useHistory } from 'react-router-dom'
import { Menu, Container,Grid } from 'semantic-ui-react'
import NaviForGuest from "./NaviForGuest"
import NaviForJobSeeker from "./NaviForJobSeeker"
import NaviForEmployer from './NaviForEmployer'
import NaviForSystemStaff from './NaviForSystemStaff'


export default function Navi() {

    const [isGuest, setIsGuest] = useState(false)
    const [isEmployer, setIsEmployer] = useState(false)
    const [isJobSeeker, setIsJobSeeker] = useState(false)
    const [isSystemStaff, setIsSystemStaff] = useState(true)

    const history = useHistory()

    function setGuest(){
       
        setIsGuest(true)
        setIsJobSeeker(false)
        setIsSystemStaff(false)
        setIsEmployer(false)
        history.push("/")
    }
   
    return (
        <div>

            
        <Menu inverted fixed="top" size="large" stackable className="center">
        <Container >
        <Menu.Item as={NavLink} to="/">
        <img  src='/logo192.png' />
        </Menu.Item>
        
       
     {isGuest ?<NaviForGuest />  : isEmployer ? <NaviForEmployer signOut={setGuest} /> : isJobSeeker ? <NaviForJobSeeker signOut={setGuest} /> : isSystemStaff ? <NaviForSystemStaff signOut={setGuest} />:''}
    
     </Container>
     </Menu>
     

           

        </div>
    )
}
