import { Button } from 'primereact/button';
import { Carousel } from 'primereact/carousel';
export default function CarouselHeader({ headLine, subHeadLine, description, carouselData }) {

    const responsiveOptions = [
        {
            breakpoint: '1400px',
            numVisible: 2,
            numScroll: 1,
        },
        {
            breakpoint: '1199px',
            numVisible: 2,
            numScroll: 1,
        },
        {
            breakpoint: '767px',
            numVisible: 1,
            numScroll: 1,
        },
        {
            breakpoint: '575px',
            numVisible: 1,
            numScroll: 1,
        },
    ];

    const carouselTemplate = (imageData) => {
        return (
            <div className="border-1 surface-border border-round m-2 text-center">
                <img
                    src={imageData.source}
                    alt={imageData.name}
                    className="w-full shadow-2"
                />
            </div>
        );
    };

    return (<div className="grid grid-nogutter text-800 p-6">
        <div className="col-12 lg:col-6 mb-4 lg:mb-0 text-center lg:text-left flex align-items-center">
            <section>
                <span className="block text-6xl font-bold mb-1">{headLine}</span>
                <div className="text-6xl text-primary font-bold mb-3">{subHeadLine}</div>
                <p className="mt-0 mb-4 text-700 line-height-3">{description}</p>

                <Button label="Login" type="button" className="mr-3 p-button-raised" />
                <Button label="All Courses" type="button" className="p-button-outlined" />
            </section>
        </div>
        <div className="card col-12 lg:col-6 overflow-hidden flex align-items-center justify-content-center">
            <Carousel value={carouselData} numScroll={1}
                numVisible={3} responsiveOptions={responsiveOptions} itemTemplate={carouselTemplate} />
        </div>
    </div>)



}