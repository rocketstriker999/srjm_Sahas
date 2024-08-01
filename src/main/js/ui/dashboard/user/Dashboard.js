import React from 'react';
import { Carousel } from 'primereact/carousel';

export default () => {

    const images = [
        'https://img-c.udemycdn.com/notices/web_carousel_slide/image/6ccb6922-a6f8-4e41-baa2-ed0cce1ba2f6.png',
        'https://img-c.udemycdn.com/notices/web_carousel_slide/image/6ccb6922-a6f8-4e41-baa2-ed0cce1ba2f6.png',
        'https://img-c.udemycdn.com/notices/web_carousel_slide/image/6ccb6922-a6f8-4e41-baa2-ed0cce1ba2f6.png'
    ];

    const responsiveOptions = [
        {
            breakpoint: '1400px',
            numVisible: 1,
            numScroll: 1
        },
        {
            breakpoint: '1199px',
            numVisible: 1,
            numScroll: 1
        },
        {
            breakpoint: '767px',
            numVisible: 1,
            numScroll: 1
        },
        {
            breakpoint: '575px',
            numVisible: 1,
            numScroll: 1
        }
    ];


    const imageTemplate = (image) => {
        return (
            <div className="carousel-item">
                <img className='w-full' src={image} alt="Random" />
            </div>
        );
    };



    return (


        <div className="w-full">
            <Carousel showNavigators={false} responsiveOptions={responsiveOptions} value={images} numVisible={1} numScroll={1} orientation="horizontal" itemTemplate={imageTemplate} />
            <h1>User Dashboard</h1>
        </div>





    );
}