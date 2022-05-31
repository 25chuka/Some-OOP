package Something;

import java.util.*;
import java.util.stream.Collectors;

public class Vehicle {
    private final int Id;
    private final int MaxSpeed;
    private final int FuelConsumption100KM;
    private final int TankVolume;

    protected Vehicle(int MaxSpeed, int FuelConsumption100KM, int TankVolume,int Id){
        this.Id = Id;
        this.MaxSpeed = MaxSpeed;
        this.FuelConsumption100KM = FuelConsumption100KM;
        this.TankVolume = TankVolume;
    }
    protected int GetMaxSpeed(){
        return MaxSpeed;
    }
    protected int GetTankVolume(){
        return TankVolume;
    }
    protected int GetId(){
        return Id;
    }


    protected void GetFuelConsumption(int Distance){
        int MaxDistance = TankVolume / FuelConsumption100KM * 100;
        if (Distance > MaxDistance){
            System.out.println("Превышение максимального объема бака : "+TankVolume+ " литров"); //?
        }else
            System.out.println("Требуемый объем : "+(double)Distance / 100 * FuelConsumption100KM+ " литров");
    }
    protected void GetSpeedOnDistance(int Distance, int TimeInTravel){
        int Speed = Distance / TimeInTravel;
        if(Speed > MaxSpeed){
            System.out.println("Превышение максимальной скорости : " +MaxSpeed+ " КМ/Ч");
        }else
            System.out.println("Требуемая скорость : "+ Speed + " КМ/Ч");
    }



}

class Car extends Vehicle{

    private final String Model;
    private final String Color;
    private  int NumOfPlaces;


    protected Car(int MaxSpeed, int FuelConsumption100KM, int TankVolume, String Model, String Color, int NumOfPlaces,int Id) {
        super(MaxSpeed, FuelConsumption100KM, TankVolume, Id);
        this.Color = Color;
        this.Model = Model;
        if(NumOfPlaces < 9){
            this.NumOfPlaces = NumOfPlaces;
        }else{
            System.out.println("Модель " + Model+" не является легковым. Объект не может быть создан.");
        }
    }
}

class Truck extends Vehicle{
    private String Type;
    private int MaxLoadCapacity;

    protected Truck(int MaxSpeed, int FuelConsumption100KM, int TankVolume,String Type,int MaxLoadCapacity,int Id) {
        super(MaxSpeed, FuelConsumption100KM, TankVolume, Id);
        this.MaxLoadCapacity = MaxLoadCapacity;
        this.Type = Type;
    }
}

class Test{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите дистанцию (км) : ");
        int Distance = sc.nextInt();
        System.out.print("Введите время в пути (час) : ");
        int TimeInTravel = sc.nextInt();
        Vehicle car = new Car(120, 50,1000, "BMW","Green", 8,1);
        Vehicle car1 = new Car(17, 50,100, "Lada", "Pink", 500,2);

        Vehicle truck = new Truck(70,50,500,"Cargo",500,3);
        Vehicle truck1 = new Truck(70,50,1500,"Tractor",50,4);

        car.GetFuelConsumption(Distance);
        car1.GetFuelConsumption(Distance);

        car.GetSpeedOnDistance(Distance,TimeInTravel);
        car1.GetSpeedOnDistance(Distance,TimeInTravel);

        List<Vehicle> VehicleList = new ArrayList<>();
        VehicleList.add(car);
        VehicleList.add(car1);
        VehicleList.add(truck);
        VehicleList.add(truck1);


        List SortedVehicleList = VehicleList.stream().sorted(Comparator.comparing(Vehicle::GetMaxSpeed).thenComparing(Vehicle::GetTankVolume)).collect(Collectors.toList());
        VehicleList = SortedVehicleList;

        System.out.print("Список отсортирован по Id : ");
        for(int i = 0; i < VehicleList.size(); i++){
            System.out.print(VehicleList.get(i).GetId() + " ");
        }
    }
}
