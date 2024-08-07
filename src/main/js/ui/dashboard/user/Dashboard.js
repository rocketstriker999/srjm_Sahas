
import CarouselHeader from './components/CarouselHeader';


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

    return <CarouselHeader headLine="Sahas Smart Studies" subHeadLine="Start Digital Learning" description="Testing Lines Samples" carouselData={myData} />

}
