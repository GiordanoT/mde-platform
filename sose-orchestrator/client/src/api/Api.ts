import axios, {AxiosResponse} from 'axios';
import {Json} from '../model/types';

export default class Api {

    static url(path: string): string {
        return 'http://localhost:9000/api/' + path;
    }


    static async post(path: string, obj: Json): Promise<AxiosResponse> {
        console.log('POST', Api.url(path));
        console.log('OBJ', obj);
        return await axios.post(Api.url(path), obj);
    }

    static async get(path: string): Promise<AxiosResponse> {
        console.log('GET', Api.url(path));
        return await axios.get(Api.url(path));
    }

    static async put(path: string, obj: Json): Promise<AxiosResponse> {
        console.log('PUT', Api.url(path));
        console.log('OBJ', obj);
        return await axios.put(Api.url(path), obj);
    }

    static async delete(path: string): Promise<AxiosResponse> {
        console.log('DELETE', Api.url(path));
        return await axios.delete(Api.url(path));
    }
}
