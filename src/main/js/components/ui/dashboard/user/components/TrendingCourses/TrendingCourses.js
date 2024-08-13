import { TabMenu } from 'primereact/tabmenu';
import React, { useState } from 'react';
import { useSelector } from 'react-redux';
export default function TrendingCourses() {

    const [activeIndex, setActiveIndex] = useState(0);

    const items = [
        { label: '11th' },
        { label: '12th' },
        { label: 'FY BCOM' },
        { label: 'SY BCOM' }
    ];

    return (
        <div className='surface-100 w-full flex justify-content-center align-items-center py-4 flex-column'>
            <div className='w-11'>
                <header className="">
                    <h1 className="font-bold">All Courses You Need at One Place</h1>
                    <p>Empower your learning journey with a wide range of courses tailored for your success.</p>
                </header>

                <div className="card">
                    <TabMenu

                        activeIndex={activeIndex}

                        onTabChange={(e) => setActiveIndex(e.index)}

                        pt={{
                            action: {
                                style: { backgroundColor: "transparent" }
                            },
                            menu: {
                                style: { backgroundColor: "transparent" }
                            }
                        }} model={items} />
                </div>
            </div>
        </div>);


}