import React from 'react'
import { NavLink } from 'react-router-dom'
import { Menu,Dropdown,Grid } from 'semantic-ui-react'
export default function NaviForSystemStaff({signOut}) {
    return (
        <div>
<Grid className="systemStaffCenter">

          <Menu.Item>
                
                <Dropdown pointing="top left" text="ad soyad">
                    <Dropdown.Menu>
                        <Dropdown.Item text="Profil" icon="info"/>
                        <Dropdown.Item onClick={signOut}  text="Çıkış Yap" icon="sign-out" />
                        
                    </Dropdown.Menu>
                </Dropdown>
            </Menu.Item>

        <Menu.Item
          name='confirm advertisements'
          as={NavLink} to="/Advertisement/Approve"
        >
          İş İlanlarını Onayla
        </Menu.Item>

        <Menu.Item
          name='confirm employer updates'
          as={NavLink} to="/Employer/ApproveUpdates"
        >
          İşveren güncellemelerini Onayla
        </Menu.Item>

        <Menu.Item
          name='edit'
          as={NavLink} to="/SystemStaff/Edit"
        >
          Bilgileri Güncelle
        </Menu.Item>

        
        </Grid>
        </div>
    )
}
