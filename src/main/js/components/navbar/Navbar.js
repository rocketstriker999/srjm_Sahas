import React from 'react';
import { Link, NavLink, useNavigate } from "react-router-dom";
import { Menubar } from 'primereact/menubar';
import { InputText } from 'primereact/inputtext';
import { Avatar } from 'primereact/avatar';
import Brand from "./Brand/Brand";
import { Badge } from 'primereact/badge';
import { isVisible } from '@testing-library/user-event/dist/utils';
import { useSelector } from 'react-redux';

export default function Navbar() {
    const navigate = useNavigate();
    const currentUser = useSelector((state) => state.auth.user);

    const itemRenderer = (item) => (
        <Link to={item.to} className="flex align-items-center p-menuitem-link">
            <span className={item.icon} />
            <span className="mx-2">{item.label}</span>
            {item.badge && <Badge className="ml-auto" value={item.badge} />}
            {item.shortcut && <span className="ml-auto border-1 surface-border border-round surface-100 text-xs p-1">{item.shortcut}</span>}
        </Link>
    );



    const items = [

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
            command: () => navigate('/login'),
            visible: !currentUser

        },
        {
            label: 'Signup',
            icon: 'pi pi-fw pi-user',
            command: () => navigate('/signup'),
            visible: !currentUser
        },
        {
            label: 'Manage',
            icon: 'pi pi-fw pi-cog',
            command: () => navigate('/signup'),
            visible: currentUser

        },
        {
            label: 'Contact',
            icon: 'pi pi-fw pi-envelope',
            command: () => navigate('/contact-us'),
            visible: !currentUser

        },
        {
            label: 'Help',
            icon: 'pi pi-fw pi-question-circle',
            command: () => navigate('/contact-us'),
            visible: currentUser

        },
        {
            label: 'Notification',
            icon: 'pi pi-bell',
            badge: 3,
            template: itemRenderer,
            to: '/profile',
            visible: currentUser
        },
    ];


    const end = currentUser ? (
        <div className="flex align-items-center gap-3">
            <InputText placeholder="Search" type="text" className="w-8rem sm:w-auto" />
            <Avatar image="https://primefaces.org/cdn/primereact/images/avatar/amyelsner.png" shape="circle" />
        </div>
    ) : null;



    return (
        <div className="card px-4 mt-4">
            <Menubar className='px-4' start={<Brand />} model={items} end={end} />
        </div>
    )


}