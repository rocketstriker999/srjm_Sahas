import React from 'react';
import { useNavigate } from "react-router-dom";
import { Menubar } from 'primereact/menubar';
import { InputText } from 'primereact/inputtext';
import { Avatar } from 'primereact/avatar';
import Brand from "./Brand/Brand";
import { Badge } from 'primereact/badge';

export default function Navbar() {
    const navigate = useNavigate();

    const itemRenderer = (item) => (
        <a className="flex align-items-center p-menuitem-link">
            <span className={item.icon} />
            <span className="mx-2">{item.label}</span>
            {item.badge && <Badge className="ml-auto" value={item.badge} />}
            {item.shortcut && <span className="ml-auto border-1 surface-border border-round surface-100 text-xs p-1">{item.shortcut}</span>}
        </a>
    );



    const items = [
        // {
        //     label: 'About',
        //     icon: 'pi pi-fw pi-info-circle',
        //     command: () => navigate('/about'),
        //     // if authorized needs to make visible:false  

        // },
        {
            label: 'Courses',
            icon: 'pi pi-fw pi-book',
            items: [
                {
                    label: '11th',
                    icon: 'pi pi-palette',
                },
                {
                    label: '12th',
                    icon: 'pi pi-palette'
                }
            ]
        },

        {
            label: 'Login',
            icon: 'pi pi-fw pi-sign-in',
            command: () => navigate('/login')
            // if authorized needs to make visible:false  

        },
        {
            label: 'Signup',
            icon: 'pi pi-fw pi-user',
            command: () => navigate('/signup')
            // if authorized needs to make visible:false  

        },
        {
            label: 'Manage',
            icon: 'pi pi-fw pi-cog',
            command: () => navigate('/signup')
            // if authorized and fadmin needs to make visible:true 

        },
        {
            label: 'Contact',
            icon: 'pi pi-fw pi-envelope',
            command: () => navigate('/contact-us')
            // if authorized and fadmin needs to make visible:true 

        },
        {
            label: 'Help',
            icon: 'pi pi-fw pi-question-circle',
            command: () => navigate('/contact-us')
            // if authorized and fadmin needs to make visible:true 

        },
        {
            label: 'Notification',
            icon: 'pi pi-bell',
            badge: 3,
            template: itemRenderer
        },
    ];


    const end = (
        <div className="flex align-items-center gap-3">
            <InputText placeholder="Search" type="text" className="w-8rem sm:w-auto" />
            <Avatar image="https://primefaces.org/cdn/primereact/images/avatar/amyelsner.png" shape="circle" />
        </div>
    );

    // const end = isAuthorized ? (
    //     <div className="flex align-items-center gap-3">
    //         <InputText placeholder="Search" type="text" className="w-8rem sm:w-auto" />
    //         <Avatar image="https://primefaces.org/cdn/primereact/images/avatar/amyelsner.png" shape="circle" />
    //     </div>
    // ) : null;

    return (
        <div className="card px-4 mt-4">
            <Menubar className='px-4' start={<Brand />} model={items} end={end} />
        </div>
    )


}