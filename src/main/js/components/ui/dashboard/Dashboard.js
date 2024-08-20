
import CarouselHeader from './components/CarouselHeader';
import Courses from './components/Courses';
import Testimonials from './components/Testimonials';
import Trends from './components/Trends';

export default function Dashboard() {


    return (
        <>
            <div className='flex justify-content-center'>
                <div className='w-8 py-8'>
                    <CarouselHeader />
                </div>
            </div>
            <div className='flex justify-content-center surface-100'>
                <div className='w-8 py-6 '>
                    <Courses />
                </div>
            </div>

            <div className='flex justify-content-center'>
                <div className='w-8 py-6'>
                    <Trends />
                </div>
            </div>

            <div className='flex justify-content-center surface-100'>
                <div className='w-8 py-6'>
                    <Testimonials />
                </div>
            </div>
        </>

    );

}
