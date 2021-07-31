import React from 'react'
import { NavLink } from 'react-router-dom'
import { Menu, Container, Dropdown, Image, Grid } from 'semantic-ui-react'
import Favorites from './Favorites'
import Navi from './Navi'
export default function NaviForJobSeeker({ signOut }) {
    return (
        <div>
            <Grid className="jobSeekerCenter">
                <Menu.Item>
                    <Image avatar spaced="right" src="https://thispersondoesnotexist.com/image" />
                    <Dropdown pointing="top left" text="ad soyad">
                        <Dropdown.Menu>
                            <Dropdown.Item text="Profil" icon="info" />
                            <Dropdown.Item onClick={signOut} text="Çıkış Yap" icon="sign-out" />

                        </Dropdown.Menu>
                    </Dropdown>
                </Menu.Item>

                <Menu.Item as={NavLink} to="/JobSeeker/Cv/Add" as={NavLink} 
                    name='create cv'
                >
                    Cv'ye bilgi ekle
                </Menu.Item>
                <Menu.Item as={NavLink} to="/JobSeeker/Cv/Edit" as={NavLink} 
                    name='edit cv'
                >
                    Cv güncelle
                </Menu.Item>
               
               <Menu.Item>
                   <Favorites></Favorites>  
               </Menu.Item>
                                             

            </Grid>

            
        </div>
    )
}
