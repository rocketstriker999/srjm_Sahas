
import CarouselHeader from './components/CarouselHeader';
import TrendingCourses from './components/Courses';

export default function Dashboard() {


    return (
        <>
            <div className='flex justify-content-center'>
                <div className='w-8 py-6'>
                    <CarouselHeader />
                </div>
            </div>
            <div className='flex justify-content-center surface-100'>
                <div className='w-8 py-6 '>
                    <TrendingCourses />
                </div>
            </div>
        </>

    );

}
