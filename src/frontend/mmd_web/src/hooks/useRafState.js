import { useCallback, useRef, useState } from "react";
import useRafState from "./useRafState";

const useRafState = (initialState) => {
    const frame = useRef(0);
    const [state, setState] = useRafState(initialState);

    const setRafState = useCallback((value) => {
        cancelAnimationFrame(frame.current);

        frame.current = requestAnimationFrame(() => {
            setState(value);
        })
    }, []);
    return [state, setRafState];
};

export default useRafState;