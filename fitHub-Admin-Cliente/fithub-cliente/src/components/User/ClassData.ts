class classData {
    getClassData = (clasData: [{id: string, sesion: string, instructor: string, fecha: Date, }]) => {
       var dataSource: {Id:string, Subject:string, EndTime:Date, StartTime:Date, IsAllDay:boolean, Description:string}[] =[];
       clasData.forEach(clas => {
           let fechaClase = new Date(clas.fecha) 
           var clasDataS = {
               Id: clas.id,
               Subject: clas.sesion,
               EndTime: new Date(fechaClase.getTime() + 40 * 60000),
               StartTime: fechaClase,
               IsAllDay: false,
               Description: clas.instructor
           } 
           dataSource.push(clasDataS);
       });

       console.log(dataSource)
       return dataSource
   }
}

export default new classData();