import { type StructurePublicCategoryRecord } from "./StructurePublicCategoryRecord"

export type StructurePublicProductRecord = {
  id: string
  name?: string
  tags?: string[]
  description?: string
  price?: string
  ean13?: string
  categoryId?: string
  category?: StructurePublicCategoryRecord
}
