import { useEffect, useRef, useState } from "react"
const useScroll = () => {
    const [state, setState] = useState({ x: 0, y: 0});
    const ref = useRef(null);

    useEffect(() => {
        const element = ref.current;
        if (!element) return;
        
        const handleScroll = () => {
            setState({
                x: ref.current.scrollLeft,
                y: ref.current.scrollTop
            })
        }
        element.addEventListener('scroll', handleScroll, { passive: true});
        // element.addEvnetListener('scroll', handleScroll, { passive: true});
        return () => {
            element.removeEventListener('scroll', handleScroll);
        };
    }, [ref]);
    return [ref, setState];
};

export default useScroll;