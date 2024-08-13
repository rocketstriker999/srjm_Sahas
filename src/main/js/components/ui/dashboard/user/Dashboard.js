
import CarouselHeader from './components/CarouselHeader';
import { Divider } from 'primereact/divider';
import TrendingCourses from './components/TrendingCourses/TrendingCourses';

export default function Dashboard() {

    const myData = [
        {
            name: "img1",
            source: "https://placehold.co/100x100"
        },
        {
            name: "img2",
            source: "https://placehold.co/100x100"
        },
        {
            name: "img3",
            source: "https://placehold.co/100x100"
        },
        {
            name: "img1",
            source: "https://placehold.co/100x100"
        },
        {
            name: "img2",
            source: "https://placehold.co/100x100"
        },
        {
            name: "img3",
            source: "https://placehold.co/100x100"
        }
    ]

    return (<>
    
        <CarouselHeader  headLine="Sahas Smart Studies" subHeadLine="Learn Digitally" description="In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content." carouselData={myData} />
        <TrendingCourses/>
        
    </>)

}
