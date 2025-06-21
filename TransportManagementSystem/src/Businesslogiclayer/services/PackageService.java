package Businesslogiclayer.services;
import Dataaccesslayer.repositories.PackageRepository;
import Dataaccesslayer.models.Package;

import java.util.List;

public class PackageService {
    // Singleton instance for the PackageService
    private static PackageService instance = null;

    // Singleton instance for the PackageRepository
    private static final PackageRepository repositoryInstance = PackageRepository.getInstance();

    // Private constructor to prevent direct instantiation
    private PackageService() {
    }

    // Method to get the singleton instance of PackageService
    public static PackageService getInstance() {
        if (PackageService.instance == null) {
            PackageService.instance = new PackageService();
        }
        return PackageService.instance;
    }

    // Method to retrieve a list of all packages
    public List<Package> getPackages() {
        return repositoryInstance.getPackages();
    }

    // Method to retrieve a package by its ID
    public Package getPackageById(int id) {
        return repositoryInstance.getPackageById(id);
    }

    // Method to add a new package
    public void addPackage(Package pack) {
        repositoryInstance.addPackage(pack);
    }

    // Method to update an existing package
    public void updatePackage(Package pack) {
        repositoryInstance.updatePackage(pack);
    }

    // Method to delete a package by its ID
    public void deletePackage(int id) {
        repositoryInstance.deletePackage(id);
    }

    // Method to retrieve a list of all package IDs
    public List<Integer> getPackageIds() {
        return repositoryInstance.getPackageIds();
    }
}