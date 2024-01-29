#include <stdbool.h>
#include <math.h>
#include "SDL.h"
#include <stdio.h>
#include <stdlib.h>
#include <curl/curl.h>

#define COORDINATES 1000
#define SHAPES 20

int cordCount = 0;
int currentShape = 0;

typedef struct Coordinates {
    float xCord;
    float yCord;
} Cord;

struct MemoryStruct {
    char *memory;
    size_t size;
};

struct Node
{
    SDL_Rect rectangle;
    int size;
    bool isIntersectingShape;
    bool isScanned;
};

struct Node** nodes;
struct Node** intersectNodeHolder;
struct Node* intersectNodes;
struct Node* optimumNode;

int intersectAmount = 0;





/*

void neighbourCheck(int* rowAmount, int* columnAmount, int* amountOfRectInBigRect, const int* amountOfRectInBigRectHolder, SDL_Rect* currentNode, Cord* rectCorners, const int* rectangleSize, Cord* mainRectCenter, SDL_Renderer* renderer)
{
        rectCorners[0].xCord = mainRectCenter->xCord - ((*rectangleSize) * (*amountOfRectInBigRect)) - (*amountOfRectInBigRect-1);
        rectCorners[0].yCord = mainRectCenter->yCord - ((*rectangleSize) * (*amountOfRectInBigRect)) - (*amountOfRectInBigRect-1);

        rectCorners[1].xCord = mainRectCenter->xCord;
        rectCorners[1].yCord = mainRectCenter->yCord - ((*rectangleSize) * (*amountOfRectInBigRect)) - (*amountOfRectInBigRect-1);

        rectCorners[2].xCord = mainRectCenter->xCord;
        rectCorners[2].yCord = mainRectCenter->yCord;

        rectCorners[3].xCord = mainRectCenter->xCord - ((*rectangleSize) * (*amountOfRectInBigRect)) - (*amountOfRectInBigRect-1);
        rectCorners[3].yCord = mainRectCenter->yCord;

        if (rectCorners[0].xCord >= currentNode->x + currentNode->w)
        {
            rectCorners[0].xCord = currentNode->x + currentNode->w;
            rectCorners[1].xCord = currentNode->x + currentNode->w + ((*rectangleSize) * (*amountOfRectInBigRect)) + (*amountOfRectInBigRect-1);
            rectCorners[2].xCord = currentNode->x + currentNode->w + ((*rectangleSize) * (*amountOfRectInBigRect)) + (*amountOfRectInBigRect-1);
            rectCorners[3].xCord = currentNode->x + currentNode->w;
        }

        if (rectCorners[0].yCord >= currentNode->y + currentNode->w)
        {
            rectCorners[0].yCord = currentNode->y + currentNode->w;
            rectCorners[1].yCord = currentNode->y + currentNode->w;
            rectCorners[2].yCord = currentNode->y + currentNode->w + ((*rectangleSize) * (*amountOfRectInBigRect)) + (*amountOfRectInBigRect-1);
            rectCorners[3].yCord = currentNode->y + currentNode->w + ((*rectangleSize) * (*amountOfRectInBigRect)) + (*amountOfRectInBigRect-1);
        }

        if ((rectCorners[1].xCord - rectCorners[0].xCord) <= currentNode->w / 2)
        {
            (*amountOfRectInBigRect) = (*amountOfRectInBigRectHolder);

            rectCorners[0].xCord = mainRectCenter->xCord - ((*rectangleSize) * (*amountOfRectInBigRect)) - (*amountOfRectInBigRect-1);
            rectCorners[0].yCord = mainRectCenter->yCord - ((*rectangleSize) * (*amountOfRectInBigRect)) - (*amountOfRectInBigRect-1);

            rectCorners[1].xCord = mainRectCenter->xCord;
            rectCorners[1].yCord = mainRectCenter->yCord - ((*rectangleSize) * (*amountOfRectInBigRect)) - (*amountOfRectInBigRect-1);

            rectCorners[2].xCord = mainRectCenter->xCord;
            rectCorners[2].yCord = mainRectCenter->yCord;

            rectCorners[3].xCord = mainRectCenter->xCord - ((*rectangleSize) * (*amountOfRectInBigRect)) - (*amountOfRectInBigRect-1);
            rectCorners[3].yCord = mainRectCenter->yCord;

            if (rectCorners[0].xCord - currentNode->w < currentNode->x && rectCorners[0].yCord + (rectCorners[1].xCord - rectCorners[0].xCord) <= currentNode->y)
            {
                mainRectCenter->xCord += (*rectangleSize+1);
            }

            else if (rectCorners[0].xCord  >= currentNode->x + currentNode->w && rectCorners[0].yCord < currentNode->y + currentNode->w)
            {
                mainRectCenter->yCord += (*rectangleSize + 1);
            }

            else if (rectCorners[0].xCord + (rectCorners[1].xCord - rectCorners[0].xCord) <= currentNode->x && rectCorners[0].yCord > currentNode->y - (rectCorners[1].xCord - rectCorners[0].xCord))
            {
                mainRectCenter->yCord -= (*rectangleSize + 1);
            }

            else if (rectCorners[0].xCord + (rectCorners[1].xCord - rectCorners[0].xCord) >= currentNode->x && rectCorners[0].yCord >= currentNode->y + currentNode->w)
            {
                mainRectCenter->xCord -= (*rectangleSize + 1);
            }
        }

        SDL_SetRenderDrawColor(renderer, 255, 0, 0,255);
        SDL_RenderDrawLine(renderer, rectCorners[0].xCord, rectCorners[0].yCord, rectCorners[1].xCord, rectCorners[1].yCord);
        SDL_RenderDrawLine(renderer, rectCorners[1].xCord, rectCorners[1].yCord, rectCorners[2].xCord, rectCorners[2].yCord);
        SDL_RenderDrawLine(renderer, rectCorners[2].xCord, rectCorners[2].yCord, rectCorners[3].xCord, rectCorners[3].yCord);
        SDL_RenderDrawLine(renderer, rectCorners[3].xCord, rectCorners[3].yCord, rectCorners[0].xCord, rectCorners[0].yCord);

    (*amountOfRectInBigRect) /= 2;
}

*/

int isPointInsidePolygon(Cord point, Cord * polygon, const int *numVertices) {
    int i, j, c = 0;
    for (i = 0, j = *numVertices - 1; i < *numVertices; j = i++)
    {
        bool isYInRangeI = ((polygon[i].yCord <= point.yCord) && (point.yCord < polygon[j].yCord));
        bool isYInRangeJ = ((polygon[j].yCord <= point.yCord) && (point.yCord < polygon[i].yCord));
        bool isXInRange = (point.xCord < (polygon[j].xCord - polygon[i].xCord) * (point.yCord - polygon[i].yCord) / (polygon[j].yCord - polygon[i].yCord) + polygon[i].xCord);
        if (( isYInRangeI || isYInRangeJ) && isXInRange)
            c = !c;
    }
    return c;
}

void setRectSideLinesCord(Cord *sideLines, struct Node *currentNode, int rectSize){
    sideLines[0].xCord = currentNode->rectangle.x+1;
    sideLines[0].yCord = currentNode->rectangle.y;
    sideLines[1].xCord = currentNode->rectangle.x + rectSize+1;
    sideLines[1].yCord = currentNode->rectangle.y+1;
    sideLines[2].xCord = currentNode->rectangle.x + rectSize;
    sideLines[2].yCord = currentNode->rectangle.y + rectSize;
    sideLines[3].xCord = currentNode->rectangle.x;
    sideLines[3].yCord = currentNode->rectangle.y + rectSize+1;
}
void optimumSquareAreaOfShape(const int* rowAmount, const int* columnAmount, const int* rectangleSize, SDL_Renderer * renderer,int* optimumIndex)
{
    int optimumRectSize = 16;
    int rectSize = (optimumRectSize * (*rectangleSize)) + (optimumRectSize-1);
    int amountOfRectInOptimumRect = 0;
    int intersectingIndex = 0;
    int scanTime = 4; // 16x16 - 4x4

    SDL_Rect neighbourRect;

    Cord rectCornerCords[4];

    for (int i = 0; i < scanTime; ++i)
    {
        for (int m = 0; m < intersectAmount; ++m)
        {
            setRectSideLinesCord(rectCornerCords, &intersectNodes[m], rectSize);
            for (int k = 0; k < *rowAmount; ++k)
            {
                for (int l = 0; l < *columnAmount; ++l)
                {
                    bool isXLessThanMax = (nodes[k][l].rectangle.x < (rectCornerCords[0].xCord + rectSize));
                    bool isXBiggerThanMin = (nodes[k][l].rectangle.x+1 >= (rectCornerCords[0].xCord));
                    bool isYLessThanMax = (nodes[k][l].rectangle.y < (rectCornerCords[0].yCord + rectSize));
                    bool isYBiggerThanMin = (nodes[k][l].rectangle.y+1 >= (rectCornerCords[0].yCord));

                    if (isXBiggerThanMin && isXLessThanMax && isYLessThanMax && isYBiggerThanMin && nodes[k][l].isIntersectingShape == true && nodes[k][l].isScanned == false)
                    {
                        amountOfRectInOptimumRect++;
                        intersectNodeHolder[intersectingIndex] = &nodes[k][l];
                        intersectingIndex++;
                    }
                }
            }

            if (amountOfRectInOptimumRect == (optimumRectSize * optimumRectSize))
            {
                for (int j = 0; j < (optimumRectSize * optimumRectSize); ++j)
                {
                    intersectNodeHolder[j]->isScanned = true;
                }

                struct Node newNode;
                newNode.rectangle.x = intersectNodes[m].rectangle.x;
                newNode.rectangle.y = intersectNodes[m].rectangle.y;
                newNode.rectangle.h = rectSize;
                newNode.rectangle.w = rectSize;
                newNode.size = optimumRectSize;

                optimumNode[*optimumIndex] = newNode;
                (*optimumIndex)++;


            }

            intersectingIndex = 0;
            amountOfRectInOptimumRect = 0;
        }

        optimumRectSize /= 2;
        rectSize = (optimumRectSize * (*rectangleSize)) + (optimumRectSize-1);
    }
}

void setRectAndPointColors(const int windowWidth,
                           const int windowHeight,
                           Cord* polygon,
                           const int numVertices,
                           const int rowAmount,
                           const int columnAmount,
                           const int rectangleSize,
                           bool isPaintRectInShape,
                           int* amountOfIntersectRect,
                           int* totalDrillingNum,
                           int* sondaj)
{
    if (nodes == NULL) { return; }


    int indexOfNode = 0;

    for (int i = 0; i < windowWidth; ++i)
    {
        for (int j = 0; j < windowHeight; ++j)
        {
            Cord currentCord;
            currentCord.xCord = i;
            currentCord.yCord = j;

            if (isPointInsidePolygon(currentCord, polygon, &numVertices))
            {
                for (int k = 0; k < rowAmount; ++k) {
                    for (int l = 0; l < columnAmount; ++l)
                    {
                        bool isXLessThanMax = (currentCord.xCord < (nodes[k][l].rectangle.x + rectangleSize));
                        bool isXBiggerThanMin = (currentCord.xCord > (nodes[k][l].rectangle.x));
                        bool isYLessThanMax = (currentCord.yCord < (nodes[k][l].rectangle.y + rectangleSize));
                        bool isYBiggerThanMin = (currentCord.yCord > (nodes[k][l].rectangle.y));

                        if ( isXBiggerThanMin && isXLessThanMax && isYLessThanMax && isYBiggerThanMin && isPaintRectInShape)
                        {
                            nodes[k][l].isIntersectingShape = true;
                        }
                    }
                }
            }
        }
    }

    for (int k = 0; k < rowAmount; ++k) {
        for (int l = 0; l < columnAmount; ++l)
        {
            if (nodes[k][l].isIntersectingShape == true)
            {
                intersectAmount++;
            }
        }
    }

    *amountOfIntersectRect = intersectAmount;
    intersectNodes = (struct Node*) malloc(sizeof(struct Node) * intersectAmount);
    optimumNode = (struct Node*) malloc(sizeof(struct Node) * (columnAmount * rowAmount));

    for (int k = 0; k < rowAmount; k++) {
        for (int l = 0; l < columnAmount; l++)
        {
            if (nodes[k][l].isIntersectingShape == true)
            {
                intersectNodes[indexOfNode] = nodes[k][l];
                indexOfNode++;
            }
        }
    }


    *sondaj = indexOfNode;







}

float polygonArea(Cord *coordinates, int count) {
    float area = 0.0;
    int i;

    for (i = 0; i < count - 1; i++) {
        area += (coordinates[i].xCord * coordinates[i + 1].yCord) - (coordinates[i + 1].xCord * coordinates[i].yCord);
    }

    area += (coordinates[count - 1].xCord * coordinates[0].yCord) - (coordinates[0].xCord * coordinates[count - 1].yCord);

    return fabs(area) / 2.0;
}

static size_t WriteMemoryCallback(void *contents, size_t size, size_t nmemb, void *userp) {
    size_t realsize = size * nmemb;
    struct MemoryStruct *mem = (struct MemoryStruct *)userp;

    char *ptr = realloc(mem->memory, mem->size + realsize + 1);
    if(ptr == NULL) {
        printf("not enough memory ");
        return 0;
    }

    mem->memory = ptr;
    memcpy(&(mem->memory[mem->size]), contents, realsize);
    mem->size += realsize;
    mem->memory[mem->size] = 0;

    return realsize;
}

int main(int argc, char *argv[]) {

    Cord coordinates[SHAPES][COORDINATES];
    int coordCounts[SHAPES] = {0};
    int numVertices = 0;
    Cord newCord;

    const char *windowTitle = "ProLab Project1";
    int windowWidth = 800;
    int windowHeight = 600;
    int totalDrillingAmount = 0;
    int sondaj = 0;
    int platform =0;
    int optimumIndex=0;


    int rectangleSize = 9;
    int spaceBtwn2Rect = 1;

    bool bQuit = false;
    bool paintRect = true;
    int amountOfIntersectRect = 0;

    int columnAmount = windowWidth / (rectangleSize + spaceBtwn2Rect);
    int rowAmount = windowHeight / (rectangleSize + spaceBtwn2Rect);

    intersectNodeHolder = (struct Node**)malloc(sizeof(struct Node*) * (columnAmount * rowAmount));
    nodes = (struct Node **) malloc(sizeof(struct Node *) * rowAmount);

    SDL_Rect rectangles[rowAmount][columnAmount];

    for (int j = 0; j < rowAmount; ++j) {
        nodes[j] = (struct Node *) malloc(sizeof(struct Node) * columnAmount);
    }

    for (int i = 0; i < rowAmount; ++i) {
        for (int j = 0; j < columnAmount; ++j) {
            nodes[i][j].rectangle.h = rectangleSize;
            nodes[i][j].rectangle.w = rectangleSize;
            nodes[i][j].rectangle.x = j * (spaceBtwn2Rect + rectangleSize);
            nodes[i][j].rectangle.y = i * (spaceBtwn2Rect + rectangleSize);
            nodes[i][j].isIntersectingShape = false;
        }
    }


    CURL *curl;
    CURLcode res;

    struct MemoryStruct chunk;
    chunk.memory = malloc(1);
    chunk.size = 0;

    curl_global_init(CURL_GLOBAL_ALL);
    curl = curl_easy_init();
    char *page = NULL;
    int lineNumber;
    float birimSondajMaliyeti;
    float birimPlatformMaliyeti;
    char lineNum;


    scanf("%d", &lineNumber);
    scanf("%f",&birimSondajMaliyeti);
    scanf("%f",&birimPlatformMaliyeti);


    if (curl) {
        curl_easy_setopt(curl, CURLOPT_URL, "https://bilgisayar.kocaeli.edu.tr/prolab1/prolab1.txt");
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteMemoryCallback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, (void *) &chunk);

        res = curl_easy_perform(curl);
        if (res != CURLE_OK) {
            fprintf(stderr, "curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
        }

        curl_easy_cleanup(curl);

        page = strtok(chunk.memory, "\n");


        printf("%c", lineNum);
        int cordIndex = 0;
        int control = 0;


        for (int i = 1; i <= lineNumber; i++)
        {
            lineNum = i + '0';
            char *ptr = strchr(page, lineNum);
            char *ptr2 = strchr(ptr, 'B');



            if (ptr2)
            {
                ptr2++;


                Cord firstPoint;
                while (*ptr2 != 'F' && coordCounts[currentShape] < COORDINATES)
                {
                    if ((sscanf(ptr2, "(%f,%f)", &coordinates[currentShape][cordCount].xCord,
                                &coordinates[currentShape][cordCount].yCord) == 2) &&
                        (i == lineNumber))
                    {
                        coordinates[currentShape][cordCount].xCord *= 10;
                        coordinates[currentShape][cordCount].yCord *= 10;

                        firstPoint.xCord = coordinates[currentShape][0].xCord;
                        firstPoint.yCord = coordinates[currentShape][0].yCord;

                        if( control == 1 && (firstPoint.xCord) == (coordinates[currentShape][cordCount].xCord) && ((firstPoint.yCord) == (coordinates[currentShape][cordCount].yCord))){
                            coordCounts[currentShape] = (cordCount+1);
                            currentShape++;
                            cordCount = -1;
                        }



                        cordCount++;
                        control = 1;

                        if (cordCount == 0)
                        {
                            control = 0;
                        }
                    }

                    ptr2 = strchr(ptr2, ')');
                    if (ptr2) ptr2++;
                }
            }

            page = strtok(NULL, "\n");
        }
    }

    int s = 0;
    double reserve=0;
    while (s < currentShape) {

        double area = polygonArea(coordinates[s], coordCounts[s]);
         reserve = area * 10;
        printf("Seklin Alani: %f\n", area);
        printf("Kaynak Rezerv Miktari: %f\n\n", reserve);
        s++;
    }

    numVertices = sizeof(coordCounts) / sizeof(Cord);

    for (int i = 0; i < rowAmount; ++i) {
        for (int j = 0; j < columnAmount; ++j) {
            rectangles[i][j].h = rectangleSize;
            rectangles[i][j].w = rectangleSize;
            rectangles[i][j].x = j * (spaceBtwn2Rect + rectangleSize);
            rectangles[i][j].y = i * (spaceBtwn2Rect + rectangleSize);
        }
    }

    if (SDL_Init(SDL_INIT_VIDEO) > 0) {
        return -1;
    }

    SDL_Window *window = SDL_CreateWindow(windowTitle, SDL_WINDOWPOS_UNDEFINED, SDL_WINDOWPOS_UNDEFINED, windowWidth,
                                          windowHeight, 0);
    if (window == NULL) {
        SDL_Quit();
        return -1;
    }

    SDL_Renderer *renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_PRESENTVSYNC);
    if (renderer == NULL) {
        SDL_DestroyWindow(window);
        SDL_Quit();
    }



    for (int i = 0; i < currentShape; ++i)
    {

        setRectAndPointColors(windowWidth, windowHeight, coordinates[i], numVertices, rowAmount, columnAmount, rectangleSize, paintRect, &amountOfIntersectRect, &totalDrillingAmount, &sondaj);

    }

    optimumSquareAreaOfShape(&rowAmount, &columnAmount, &rectangleSize, renderer,&optimumIndex);


    printf("Toplam Sondaj sayısı: %d\n", sondaj);
    double toplamSondaj= sondaj*birimSondajMaliyeti;
    printf("Toplam sondaj maliyeti: %f \n",toplamSondaj);
    printf("Toplam platform sayısı: %d \n",optimumIndex);
    double toplamPlatform = optimumIndex*birimPlatformMaliyeti;
    printf("Toplam platform sayisi: %f \n",toplamPlatform);
    double toplamMaliyet = toplamSondaj+toplamPlatform;
    printf("Toplam maliyet: %f \n",toplamMaliyet);
    double karMiktari = reserve - toplamMaliyet;
    printf("Kar miktari: %f",karMiktari);

/*
    int amountOfRectInBigRect = 8;
    int amountOfRectInBigRectHolder = amountOfRectInBigRect;
    Cord lastPos;
    Cord mainRectCenter;
    Cord neighbourRect[4];
    SDL_Rect rect;
    rect.h = (rectangleSize * 4) + 4 - 1;
    rect.w = (rectangleSize * 4) + 4 - 1;
    rect.x = windowWidth / 2 - ((rect.w) - 1) * 2;
    rect.y = windowHeight / 2 - (rect.h) - 1 * 2;
    neighbourRect[0].xCord = rect.x - rect.h-1;
    neighbourRect[0].yCord = rect.y - rect.h-1;

    neighbourRect[1].xCord = rect.x-1;
    neighbourRect[1].yCord = rect.y - rect.h-1;

    neighbourRect[2].xCord = rect.x-1;
    neighbourRect[2].yCord = rect.y-1;

    neighbourRect[3].xCord = rect.x - rect.w-1;
    neighbourRect[3].yCord = rect.y-1;
*/


    while (!bQuit) {
        SDL_RenderClear(renderer);

        SDL_Event event;
        while (SDL_PollEvent(&event)) {
            if (event.type == SDL_QUIT) {
                bQuit = true;
            }
        }

        for (int i = 0; i < rowAmount; ++i) {
            for (int j = 0; j < columnAmount; ++j) {
                Cord cord;
                cord.xCord = nodes[i][j].rectangle.x;
                cord.yCord = nodes[i][j].rectangle.y;

                bool isInsideShape = false;
                Cord checkPoints[5];
                checkPoints[0].xCord = nodes[i][j].rectangle.x;
                checkPoints[0].yCord = nodes[i][j].rectangle.y;
                checkPoints[1].xCord = nodes[i][j].rectangle.x + nodes[i][j].rectangle.w;
                checkPoints[1].yCord = nodes[i][j].rectangle.y;
                checkPoints[2].xCord = nodes[i][j].rectangle.x;
                checkPoints[2].yCord = nodes[i][j].rectangle.y + nodes[i][j].rectangle.h;
                checkPoints[3].xCord = nodes[i][j].rectangle.x + nodes[i][j].rectangle.w;
                checkPoints[3].yCord = nodes[i][j].rectangle.y + nodes[i][j].rectangle.h;
                checkPoints[4].xCord = nodes[i][j].rectangle.x + nodes[i][j].rectangle.w / 2;
                checkPoints[4].yCord = nodes[i][j].rectangle.y + nodes[i][j].rectangle.h / 2;

                for (int k = 0; k < 5; ++k)
                {
                    for (int s = 0; s < currentShape; s++)
                    {
                        if (isPointInsidePolygon(checkPoints[k], coordinates[s], &coordCounts[s]))
                        {
                            isInsideShape = true;
                            break;
                        }
                    }
                    if (isInsideShape) break;
                }


                if (isInsideShape) {
                    SDL_SetRenderDrawColor(renderer, 0, 255, 155, 255);
                } else {
                    SDL_SetRenderDrawColor(renderer, 0, 0, 0, 255);
                }

                SDL_RenderFillRect(renderer, &nodes[i][j].rectangle);
            }
        }

        for (int s = 0; s < currentShape; s++)
        {
            for (int i = 0; i < coordCounts[s]-1; i++)
            {
                SDL_RenderDrawLine(renderer,
                                   coordinates[s][i].xCord,
                                   coordinates[s][i].yCord,
                                   coordinates[s][i+1].xCord,
                                   coordinates[s][i+1].yCord);
            }
        }

        for (int i = 0; i < optimumIndex; ++i)
        {
            if (optimumNode[i].size == 16)
            {
                SDL_SetRenderDrawColor(renderer, 0, 255, 255, 255);
            }

            else if (optimumNode[i].size == 8)
            {
                SDL_SetRenderDrawColor(renderer, 0, 0, 255, 255);
            }

            else if (optimumNode[i].size == 4)
            {
                SDL_SetRenderDrawColor(renderer, 255, 0, 0, 255);
            }

            else if (optimumNode[i].size == 2)
            {
                SDL_SetRenderDrawColor(renderer, 120, 90, 170, 255);
            }

            else
                SDL_SetRenderDrawColor(renderer, 255, 0, 255, 255);

            SDL_RenderFillRect(renderer, &optimumNode[i].rectangle);
        }

/*        neighbourCheck(&rowAmount, &columnAmount, &amountOfRectInBigRect, &amountOfRectInBigRectHolder, &rect, neighbourRect, &rectangleSize, &mainRectCenter, renderer);
        SDL_SetRenderDrawColor(renderer, 255, 0,0,255);
        SDL_RenderFillRect(renderer, &rect);*/

        SDL_SetRenderDrawColor(renderer, 255, 255, 255, 255);
        SDL_RenderPresent(renderer);
    }


    for (int i = 0; i < rowAmount; ++i)
    {
        nodes[i] = (struct Node*) calloc(columnAmount, sizeof(struct Node));
    }

    nodes = (struct Node**) calloc(rowAmount, sizeof(struct Node*));
    intersectNodes = (struct Node*) calloc(intersectAmount, sizeof(struct Node));
    optimumNode = (struct Node*) calloc( ((columnAmount) * (rowAmount)), sizeof(struct Node));
    intersectNodeHolder = (struct Node**)calloc( ((columnAmount) * (rowAmount)), sizeof(struct Node*));

    SDL_DestroyRenderer(renderer);
    SDL_DestroyWindow(window);
    SDL_Quit();
    return 0;
}